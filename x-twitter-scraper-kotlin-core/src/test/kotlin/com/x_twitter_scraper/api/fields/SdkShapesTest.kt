// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.SearchTweet
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusResponse
import com.x_twitter_scraper.api.models.x.media.MediaUploadParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateAvatarParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateBannerParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SdkShapesTest {

    private fun operation(label: String): SdkOperation = operations.single { it.label == label }

    @Test
    fun recordsTheMethodAndPathEachOperationSends() {
        assertThat(operations).hasSize(183)
        assertThat(operations.map { it.label }.distinct()).hasSize(180)
        assertThat(
                operations.map {
                    val contentType = it.contentType?.substringBefore(';')
                    Triple(it.method, it.pathSegments, contentType)
                }
            )
            .doesNotHaveDuplicates()
        assertThat(operation("x().spaces().search").method).isEqualTo("GET")
        assertThat(operation("x().spaces().search").pathSegments)
            .containsExactly("x", "spaces", "search")
        assertThat(operation("x().tweets().retrieve").pathSegments)
            .containsExactly("x", "tweets", "zz-sample-a")
    }

    @Test
    fun readsQueryParamsByTheirWireNamesWithEnumsAndRequiredFlags() {
        val query = operation("x().spaces().search").query.props

        assertThat(query["q"]).isEqualTo(Prop(Shape.Leaf(), true, null))
        assertThat(query["queryType"])
            .isEqualTo(Prop(Shape.Leaf(setOf("Latest", "Top", "Live", "Upcoming")), false, null))
        assertThat(operation("x().tweets().retrieve").query.props.keys).contains("fields", "omit")
        assertThat(operation("x().getTrendsHistory").query.props.keys).contains("from")
    }

    @Test
    fun readsJsonAndMultipartBodies() {
        val ticket = operation("support().tickets().create").body as Shape.Fields
        val compose = operation("compose().create").body

        assertThat(ticket.props["body"]).isEqualTo(Prop(Shape.Leaf(), true, null))
        assertThat(ticket.props["subject"]).isEqualTo(Prop(Shape.Leaf(), true, null))
        assertThat(compose).isInstanceOf(Shape.AnyOf::class.java)
        assertThat(operation("x().tweets().retrieve").body).isNull()
    }

    @Test
    fun leavesStreamsWithoutAJsonResponse() {
        assertThat(operation("events().stream").response).isNull()
        assertThat(operation("x().tweets().retrieve").response)
            .isInstanceOf(Shape.Fields::class.java)
    }

    @Test
    fun readsAlternativeUploadInputs() {
        val reader = SdkShapes()
        listOf(
                MediaUploadParams.Body::class.java,
                ProfileUpdateAvatarParams.Body::class.java,
                ProfileUpdateBannerParams.Body::class.java,
            )
            .forEach { model ->
                val props = (reader.shapeOf(model) as Shape.Fields).props

                assertThat(props.getValue("account").required).isTrue()
                assertThat(props.getValue("url").required).isFalse()
                assertThat(props.getValue("file").required).isFalse()
            }
    }

    @Test
    fun recordsTheUploadBodyTypeAndItsSelectedInput() {
        listOf("x().media().upload", "x().profile().updateAvatar", "x().profile().updateBanner")
            .forEach { label ->
                val uploads = operations.filter { it.label == label }
                val json = uploads.single { it.contentType == "application/json" }
                val multipart = uploads.single {
                    it.contentType?.startsWith("multipart/form-data;") == true
                }
                val jsonBody = json.body as Shape.Fields
                val multipartBody = multipart.body as Shape.Fields

                assertThat(uploads).hasSize(2)
                assertThat(jsonBody.props.getValue("url").required).isTrue()
                assertThat(jsonBody.props).doesNotContainKeys("file", "is_long_video")
                assertThat(multipartBody.props.getValue("file").required).isTrue()
                assertThat(multipartBody.props).doesNotContainKey("url")
                if (label == "x().media().upload") {
                    assertThat(multipartBody.props.getValue("is_long_video").required).isFalse()
                }
            }
        assertThat(operation("support().tickets().create").contentType)
            .startsWith("multipart/form-data;")
        assertThat(operation("x().tweets().retrieve").contentType).isNull()
    }

    @Test
    fun readsRequiredAndNullableFlagsFromTheBuildersAndGetters() {
        val response = operation("x().tweets().retrieve").response as Shape.Fields
        val tweet = response.props.getValue("tweet")
        val fields = tweet.shape as Shape.Fields

        assertThat(tweet.required).isTrue()
        assertThat(tweet.nullable).isFalse()
        assertThat(fields.props.getValue("id")).isEqualTo(Prop(Shape.Leaf(), true, false))
        assertThat(fields.props.getValue("lang").required).isFalse()
        assertThat(fields.props.getValue("lang").nullable).isNull()
    }

    @Test
    fun readsNumericEnumsAndConstants() {
        val reader = SdkShapes()
        val status = reader.shapeOf(GuestWalletRetrieveStatusResponse::class.java) as Shape.Fields
        val locale = reader.shapeOf(AccountUpdateLocaleResponse::class.java) as Shape.Fields

        assertThat(status.props.getValue("poll_after_seconds").shape)
            .isEqualTo(Shape.Leaf(setOf("2")))
        assertThat(locale.props.getValue("success"))
            .isEqualTo(Prop(Shape.Leaf(setOf("true")), null, null))
    }

    @Test
    fun readsAdditionalFieldsWithoutMakingThemRequired() {
        val reader = SdkShapes()
        val tweets = reader.shapeOf(PaginatedTweets::class.java) as Shape.Fields
        val users = reader.shapeOf(PaginatedUsers::class.java) as Shape.Fields
        val tweet = reader.shapeOf(SearchTweet::class.java) as Shape.Fields

        assertThat(tweets.props.getValue("skipped").required).isFalse()
        assertThat(users.props.getValue("scanLimit").required).isFalse()
        assertThat(users.props.getValue("diagnostic").required).isFalse()
        assertThat(tweet.props.getValue("hiddenByAuthor"))
            .isEqualTo(Prop(Shape.Leaf(), false, null))
        assertThat(operation("x().users().retrieveBatch").query.props.getValue("usernames"))
            .isEqualTo(Prop(Shape.Leaf(), false, null))
    }

    @Test
    fun readsTheAdditionalMonitorEventTypes() {
        val eventType = SdkShapes().shapeOf(EventType::class.java) as Shape.Leaf

        assertThat(eventType.values)
            .contains(
                "tweet.deleted",
                "broadcast.started",
                "space.started",
                "following.added",
                "following.removed",
                "follower.added",
                "follower.removed",
                "profile.followers.changed",
                "profile.following.changed",
            )
    }

    companion object {
        private val operations: List<SdkOperation> by lazy { SdkShapes().operations() }
    }
}
