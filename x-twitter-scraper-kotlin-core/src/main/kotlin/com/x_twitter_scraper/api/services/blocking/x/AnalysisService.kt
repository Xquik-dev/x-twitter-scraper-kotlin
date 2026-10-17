// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.analysis.AnalysisBrandParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisClassifyParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisMarketSignalsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisNewsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisSentimentParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisViralScoreParams
import com.x_twitter_scraper.api.models.x.analysis.TweetAnalysisPage

/** AI analysis of posts, at 2 credits per analyzed post */
interface AnalysisService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AnalysisService

    /**
     * Answers each post's attitude, its intensity & its sarcasm probability. Send post IDs, an X
     * search, an account, a List, a post's replies or quotes, or your own texts. Each analyzed post
     * costs 2 credits. A post without an analysis costs nothing.
     */
    fun sentiment(
        params: AnalysisSentimentParams = AnalysisSentimentParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see sentiment */
    fun sentiment(requestOptions: RequestOptions): TweetAnalysisPage =
        sentiment(AnalysisSentimentParams.none(), requestOptions)

    /**
     * Answers each post's hook, clarity, payoff, humor, spam risk & likely reaction, & adds a Viral
     * Score from 0 to 100 with a verdict. The score rates the wording. It does not predict likes or
     * views. Send drafts in texts to score them before posting. Each analyzed post costs 2 credits.
     */
    fun viralScore(
        params: AnalysisViralScoreParams = AnalysisViralScoreParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see viralScore */
    fun viralScore(requestOptions: RequestOptions): TweetAnalysisPage =
        viralScore(AnalysisViralScoreParams.none(), requestOptions)

    /**
     * Answers whether each post is about your brand, the author's attitude toward it & the author's
     * relationship with it. Name the brand in analysis.targets. Each analyzed post costs 2 credits.
     */
    fun brand(
        params: AnalysisBrandParams = AnalysisBrandParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see brand */
    fun brand(requestOptions: RequestOptions): TweetAnalysisPage =
        brand(AnalysisBrandParams.none(), requestOptions)

    /**
     * Answers each post's format, such as reporting, commentary or speculation, its source
     * attribution & its relevance to your topic. Name the topic in analysis.targets. Each analyzed
     * post costs 2 credits.
     */
    fun news(
        params: AnalysisNewsParams = AnalysisNewsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see news */
    fun news(requestOptions: RequestOptions): TweetAnalysisPage =
        news(AnalysisNewsParams.none(), requestOptions)

    /**
     * Answers each post's stance on an asset, its content type, its conviction & its relevance. The
     * summary counts bullish & bearish posts per cashtag. Name the asset in analysis.targets. Each
     * analyzed post costs 2 credits.
     */
    fun marketSignals(
        params: AnalysisMarketSignalsParams = AnalysisMarketSignalsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see marketSignals */
    fun marketSignals(requestOptions: RequestOptions): TweetAnalysisPage =
        marketSignals(AnalysisMarketSignalsParams.none(), requestOptions)

    /**
     * Answers 1 to 8 questions you write in analysis.questions. A choice picks a category, such as
     * a topic. A score picks a level. A probability answers yes or no. Without questions it answers
     * the sentiment questions. Each analyzed post costs 2 credits.
     */
    fun classify(
        params: AnalysisClassifyParams = AnalysisClassifyParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetAnalysisPage

    /** @see classify */
    fun classify(requestOptions: RequestOptions): TweetAnalysisPage =
        classify(AnalysisClassifyParams.none(), requestOptions)

    /** A view of [AnalysisService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AnalysisService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/analysis/sentiment`, but is otherwise the same
         * as [AnalysisService.sentiment].
         */
        @MustBeClosed
        fun sentiment(
            params: AnalysisSentimentParams = AnalysisSentimentParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see sentiment */
        @MustBeClosed
        fun sentiment(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            sentiment(AnalysisSentimentParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/viral-score`, but is otherwise the same
         * as [AnalysisService.viralScore].
         */
        @MustBeClosed
        fun viralScore(
            params: AnalysisViralScoreParams = AnalysisViralScoreParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see viralScore */
        @MustBeClosed
        fun viralScore(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            viralScore(AnalysisViralScoreParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/brand`, but is otherwise the same as
         * [AnalysisService.brand].
         */
        @MustBeClosed
        fun brand(
            params: AnalysisBrandParams = AnalysisBrandParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see brand */
        @MustBeClosed
        fun brand(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            brand(AnalysisBrandParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/news`, but is otherwise the same as
         * [AnalysisService.news].
         */
        @MustBeClosed
        fun news(
            params: AnalysisNewsParams = AnalysisNewsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see news */
        @MustBeClosed
        fun news(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            news(AnalysisNewsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/market-signals`, but is otherwise the
         * same as [AnalysisService.marketSignals].
         */
        @MustBeClosed
        fun marketSignals(
            params: AnalysisMarketSignalsParams = AnalysisMarketSignalsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see marketSignals */
        @MustBeClosed
        fun marketSignals(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            marketSignals(AnalysisMarketSignalsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/classify`, but is otherwise the same as
         * [AnalysisService.classify].
         */
        @MustBeClosed
        fun classify(
            params: AnalysisClassifyParams = AnalysisClassifyParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetAnalysisPage>

        /** @see classify */
        @MustBeClosed
        fun classify(requestOptions: RequestOptions): HttpResponseFor<TweetAnalysisPage> =
            classify(AnalysisClassifyParams.none(), requestOptions)
    }
}
