// SPDX-FileCopyrightText: 2026 Xquik contributors
// SPDX-License-Identifier: Apache-2.0

package main

import (
	"errors"
	"net/http"
	"net/http/httptest"
	"os"
	"os/exec"
	"path/filepath"
	"strings"
	"sync/atomic"
	"testing"
)

func git(t *testing.T, dir string, args ...string) string {
	t.Helper()
	cmd := exec.Command("git", append([]string{"-c", "core.hooksPath=/dev/null"}, args...)...)
	cmd.Dir = dir
	cmd.Env = environment()
	output, err := cmd.CombinedOutput()
	if err != nil {
		t.Fatalf("git %v: %v\n%s", args, err, output)
	}
	return strings.TrimSpace(string(output))
}

func write(t *testing.T, dir, name, value string) {
	t.Helper()
	if err := os.WriteFile(filepath.Join(dir, name), []byte(value), 0600); err != nil {
		t.Fatal(err)
	}
}

func source(t *testing.T) string {
	t.Helper()
	dir := t.TempDir()
	origin := filepath.Join(t.TempDir(), "origin.git")
	git(t, dir, "init", "--bare", origin)
	git(t, dir, "init", "-b", "main")
	git(t, dir, "config", "user.name", "Release test")
	git(t, dir, "config", "user.email", "release-test@example.com")
	git(t, dir, "remote", "add", "origin", origin)
	write(t, dir, "build.gradle.kts", "allprojects { version = \"0.9.4\" // x-release-please-version\n}\n")
	write(t, dir, ".release-please-manifest.json", "{\".\":\"0.9.4\"}\n")
	write(t, dir, "CHANGELOG.md", "# Changelog\n\n## 0.9.4 (2026-10-02)\n")
	for _, name := range []string{"bin/publish-release", "scripts/release/main.go"} {
		data, err := os.ReadFile(filepath.Join("../..", name))
		if errors.Is(err, os.ErrNotExist) {
			continue
		}
		if err != nil {
			t.Fatal(err)
		}
		if err := os.MkdirAll(filepath.Dir(filepath.Join(dir, name)), 0700); err != nil {
			t.Fatal(err)
		}
		write(t, dir, name, string(data))
	}
	write(t, dir, "gradlew", "#!/usr/bin/env bash\nprintf '%s\\n' \"$*\" >> \"$GRADLE_TEST_REQUESTS\"\nexit 1\n")
	if err := os.Chmod(filepath.Join(dir, "gradlew"), 0700); err != nil {
		t.Fatal(err)
	}
	commit(t, dir)
	git(t, dir, "tag", "v0.9.4")
	git(t, dir, "push", "origin", "main", "refs/tags/v0.9.4")
	return dir
}

func commit(t *testing.T, dir string) {
	t.Helper()
	git(t, dir, "add", "--all")
	git(t, dir, "commit", "-m", "Release fixture")
}

func TestPublisherRejectsAnInvalidReleaseSource(t *testing.T) {
	for _, tc := range []struct {
		name, message string
		change        func(*testing.T, string)
	}{
		{"dirty source", "Release source has uncommitted files.", func(t *testing.T, dir string) { write(t, dir, "dirty.txt", "uncommitted") }},
		{"head differs from tag", "HEAD must match the release tag.", func(t *testing.T, dir string) {
			write(t, dir, "new.txt", "new commit")
			commit(t, dir)
			git(t, dir, "push", "origin", "main")
		}},
		{"missing tag", "Release tag is missing.", func(t *testing.T, dir string) {
			git(t, dir, "tag", "-d", "v0.9.4")
			git(t, dir, "push", "origin", ":refs/tags/v0.9.4")
		}},
		{"tag not merged", "Release commit must be merged into origin/main.", func(t *testing.T, dir string) {
			git(t, dir, "checkout", "-b", "release")
			write(t, dir, "branch.txt", "unmerged commit")
			commit(t, dir)
			git(t, dir, "tag", "-f", "v0.9.4")
			git(t, dir, "push", "--force", "origin", "refs/tags/v0.9.4")
		}},
		{"build version mismatch", "Build version must match the release tag.", func(t *testing.T, dir string) {
			write(t, dir, "build.gradle.kts", "allprojects { version = \"0.9.3\" // x-release-please-version\n}\n")
			retag(t, dir)
		}},
		{"manifest mismatch", "Release manifest must match the release tag.", func(t *testing.T, dir string) {
			write(t, dir, ".release-please-manifest.json", "{\".\":\"0.9.3\"}\n")
			retag(t, dir)
		}},
		{"changelog mismatch", "Changelog must contain the release version.", func(t *testing.T, dir string) {
			write(t, dir, "CHANGELOG.md", "# Changelog\n\n## 0.9.3 (2026-09-01)\n")
			retag(t, dir)
		}},
		{"remote tag mismatch", "Remote release tag must match the local tag.", func(t *testing.T, dir string) {
			write(t, dir, "new.txt", "new commit")
			commit(t, dir)
			git(t, dir, "tag", "-f", "v0.9.4")
			git(t, dir, "push", "origin", "main")
		}},
	} {
		t.Run(tc.name, func(t *testing.T) {
			dir := source(t)
			tc.change(t, dir)
			invoke(t, dir, []string{"v0.9.4", "--check-source"}, 1, tc.message)
		})
	}
}

func retag(t *testing.T, dir string) {
	t.Helper()
	commit(t, dir)
	git(t, dir, "tag", "-f", "v0.9.4")
	git(t, dir, "push", "--force", "origin", "main", "refs/tags/v0.9.4")
}

func invoke(t *testing.T, dir string, args []string, expected int, message string) {
	t.Helper()
	script := filepath.Join(dir, "bin/publish-release")
	cmd := exec.Command("bash", append([]string{script}, args...)...)
	cmd.Dir = dir
	requests := filepath.Join(dir, "gradle-requests")
	cmd.Env = append(environment(), "GRADLE_USER_HOME="+t.TempDir(), "GRADLE_TEST_REQUESTS="+requests)
	output, err := cmd.CombinedOutput()
	if _, statErr := os.Stat(requests); !errors.Is(statErr, os.ErrNotExist) {
		t.Fatalf("Gradle launched during source or credential validation: %v", statErr)
	}
	actual := 0
	var failure *exec.ExitError
	if errors.As(err, &failure) {
		actual = failure.ExitCode()
	} else if err != nil {
		t.Fatal(err)
	}
	if actual != expected || !strings.Contains(string(output), message) {
		t.Fatalf("expected exit %d and %q, got exit %d: %s", expected, message, actual, output)
	}
}

func TestPublisherAcceptsConsistentSourceReferences(t *testing.T) {
	invoke(t, source(t), []string{"v0.9.4", "--check-source"}, 0, "Source references are consistent.")
}

func TestPublisherRejectsMissingCredentialsBeforePublication(t *testing.T) {
	var requests atomic.Int32
	central := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		requests.Add(1)
		http.Error(w, "Unexpected publisher request", http.StatusInternalServerError)
	}))
	defer central.Close()
	t.Setenv("HTTPS_PROXY", central.URL)
	t.Setenv("https_proxy", central.URL)
	t.Setenv("NO_PROXY", "")
	t.Setenv("no_proxy", "")
	invoke(t, source(t), []string{"v0.9.4"}, 1, "Missing release credentials: SONATYPE_USERNAME, SONATYPE_PASSWORD, GPG_SIGNING_KEY, GPG_SIGNING_PASSWORD.")
	if requests.Load() != 0 {
		t.Fatalf("Central received %d requests without credentials", requests.Load())
	}
}

func environment() []string {
	var env []string
	for _, value := range os.Environ() {
		if !strings.HasPrefix(value, "GIT_") && !strings.HasPrefix(value, "SONATYPE_") && !strings.HasPrefix(value, "GPG_SIGNING_") && !strings.HasPrefix(value, "ORG_GRADLE_PROJECT_") && !strings.HasPrefix(value, "GRADLE_USER_HOME=") && !strings.HasPrefix(value, "GRADLE_TEST_REQUESTS=") {
			env = append(env, value)
		}
	}
	return append(env, "GIT_CONFIG_GLOBAL=/dev/null", "GIT_CONFIG_SYSTEM=/dev/null", "GIT_CONFIG_NOSYSTEM=1", "GIT_CONFIG_COUNT=0", "SONATYPE_USERNAME=", "SONATYPE_PASSWORD=", "GPG_SIGNING_KEY=", "GPG_SIGNING_PASSWORD=")
}
