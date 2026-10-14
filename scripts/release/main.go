// SPDX-FileCopyrightText: 2026 Xquik contributors
// SPDX-License-Identifier: Apache-2.0

package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"
	"os/exec"
	"regexp"
	"strings"
	"time"
)

const repository = "Xquik-dev/x-twitter-scraper-kotlin"
const module = "x-twitter-scraper-kotlin"

func fail(message string) { fmt.Fprintln(os.Stderr, message); os.Exit(1) }
func gitOutput(args ...string) (string, error) {
	out, err := exec.Command("git", args...).Output()
	return strings.TrimSpace(string(out)), err
}
func read(path string) []byte {
	data, err := os.ReadFile(path)
	if err != nil {
		fail("Read release metadata: " + err.Error())
	}
	return data
}
func run(name string, args ...string) {
	command := exec.Command(name, args...)
	command.Stdin, command.Stdout, command.Stderr = os.Stdin, os.Stdout, os.Stderr
	if err := command.Run(); err != nil {
		fail(name + " failed. Preserve its output before retrying.")
	}
}
func github(path string, target any) {
	data, err := exec.Command("gh", "api", "--paginate", "--slurp", "repos/"+repository+"/"+path).Output()
	if err != nil {
		fail("GitHub release qualification is unavailable.")
	}
	if err := json.Unmarshal(data, target); err != nil {
		fail("GitHub returned invalid qualification evidence.")
	}
}
func nativeChecks(head string) {
	var protection []struct {
		Strict bool `json:"strict"`
		Checks []struct {
			Context string `json:"context"`
			AppID   int    `json:"app_id"`
		} `json:"checks"`
	}
	github("branches/main/protection/required_status_checks", &protection)
	expected := map[string]bool{"Analyze Java & Kotlin": true, "Build": true, "Coverage": true, "Dependency & Build Security": true, "Dynamic Analysis": true, "Lint & Licensing": true, "Tests": true}
	if len(protection) != 1 || !protection[0].Strict || len(protection[0].Checks) != 7 {
		fail("Required native check protections are incomplete.")
	}
	var pages []struct {
		Checks []struct {
			ID         int64  `json:"id"`
			Name       string `json:"name"`
			Head       string `json:"head_sha"`
			Status     string `json:"status"`
			Conclusion string `json:"conclusion"`
			App        struct {
				ID int `json:"id"`
			} `json:"app"`
		} `json:"check_runs"`
	}
	github("commits/"+head+"/check-runs", &pages)
	for _, required := range protection[0].Checks {
		if !expected[required.Context] || required.AppID <= 0 || required.AppID == 15368 {
			fail("Required checks must bind to the approved native publisher.")
		}
		delete(expected, required.Context)
		var latest int64
		passed := false
		for _, page := range pages {
			for _, check := range page.Checks {
				if check.Name == required.Context && check.App.ID == required.AppID && check.Head == head && check.ID > latest {
					latest, passed = check.ID, check.Status == "completed" && check.Conclusion == "success"
				}
			}
		}
		if !passed {
			fail("Native qualification is missing for " + required.Context + ".")
		}
	}
	if len(expected) != 0 {
		fail("Required native check names are incomplete.")
	}
}
func main() {
	if len(os.Args) < 2 || len(os.Args) > 3 || !regexp.MustCompile(`^v[0-9]+\.[0-9]+\.[0-9]+$`).MatchString(os.Args[1]) || (len(os.Args) == 3 && os.Args[2] != "--check-source") {
		fail("Pass a stable release tag & optional --check-source.")
	}
	tag, version := os.Args[1], strings.TrimPrefix(os.Args[1], "v")
	status, err := gitOutput("status", "--porcelain", "--untracked-files=all")
	if err != nil {
		fail("Run the publisher from its Git checkout.")
	}
	if status != "" {
		fail("Release source has uncommitted files.")
	}
	tagged, err := gitOutput("rev-parse", "--verify", "refs/tags/"+tag+"^{commit}")
	if err != nil {
		fail("Release tag is missing.")
	}
	head, err := gitOutput("rev-parse", "HEAD")
	if err != nil || head != tagged {
		fail("HEAD must match the release tag.")
	}
	remote, err := gitOutput("ls-remote", "--tags", "origin", "refs/tags/"+tag, "refs/tags/"+tag+"^{}")
	remoteHead := ""
	for _, line := range strings.Split(remote, "\n") {
		fields := strings.Fields(line)
		if len(fields) == 2 {
			remoteHead = fields[0]
		}
	}
	if err != nil || remoteHead != head {
		fail("Remote release tag must match the local tag.")
	}
	if _, err := gitOutput("fetch", "--no-tags", "origin", "main"); err != nil {
		fail("Fetch origin/main before publishing.")
	}
	if _, err := gitOutput("merge-base", "--is-ancestor", head, "FETCH_HEAD"); err != nil {
		fail("Release commit must be merged into origin/main.")
	}
	declared := regexp.MustCompile(`version\s*=\s*"([^"]+)"\s*// x-release-please-version`).FindAllSubmatch(read("build.gradle.kts"), -1)
	if len(declared) != 1 || string(declared[0][1]) != version {
		fail("Build version must match the release tag.")
	}
	var manifest map[string]string
	if json.Unmarshal(read(".release-please-manifest.json"), &manifest) != nil || manifest["."] != version {
		fail("Release manifest must match the release tag.")
	}
	changelog := regexp.MustCompile(`(?m)^## ([0-9]+\.[0-9]+\.[0-9]+)\b`).FindSubmatch(read("CHANGELOG.md"))
	if len(changelog) != 2 || string(changelog[1]) != version {
		fail("Changelog must contain the release version.")
	}
	if len(os.Args) == 3 {
		fmt.Println("Source references are consistent.")
		return
	}
	var missing []string
	for _, name := range []string{"SONATYPE_USERNAME", "SONATYPE_PASSWORD", "GPG_SIGNING_KEY", "GPG_SIGNING_PASSWORD"} {
		if os.Getenv(name) == "" {
			missing = append(missing, name)
		}
	}
	if len(missing) != 0 {
		fail("Missing release credentials: " + strings.Join(missing, ", ") + ".")
	}
	nativeChecks(head)
	client := http.Client{Timeout: 30 * time.Second}
	for _, artifact := range []string{module, module + "-core", module + "-client-okhttp"} {
		response, err := client.Head("https://repo.maven.apache.org/maven2/com/xquik/api/" + artifact + "/" + version + "/" + artifact + "-" + version + ".pom")
		if err != nil {
			fail("Maven Central is unavailable. Preserve the release before retrying.")
		}
		response.Body.Close()
		if response.StatusCode == http.StatusOK {
			fail("This version already exists. Verify its signed artifacts before retrying.")
		}
		if response.StatusCode != http.StatusNotFound {
			fail("Maven Central returned an unexpected artifact status.")
		}
	}
	run("bash", "bin/check-release-environment")
	os.Setenv("ORG_GRADLE_PROJECT_mavenCentralUsername", os.Getenv("SONATYPE_USERNAME"))
	os.Setenv("ORG_GRADLE_PROJECT_mavenCentralPassword", os.Getenv("SONATYPE_PASSWORD"))
	os.Setenv("ORG_GRADLE_PROJECT_signingInMemoryKey", os.Getenv("GPG_SIGNING_KEY"))
	os.Setenv("ORG_GRADLE_PROJECT_signingInMemoryKeyPassword", os.Getenv("GPG_SIGNING_PASSWORD"))
	run("./gradlew", "publishAndReleaseToMavenCentral", "--no-daemon", "--no-build-cache", "--no-configuration-cache")
	run("bin/verify-release-signature", tag)
	fmt.Println("Verify every Maven artifact & client installation before closing the release.")
}
