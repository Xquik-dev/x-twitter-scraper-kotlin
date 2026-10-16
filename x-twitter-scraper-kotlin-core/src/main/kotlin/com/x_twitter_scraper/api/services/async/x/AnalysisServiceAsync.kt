// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.analysis.AnalysisBrandParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisBrandResponse
import com.x_twitter_scraper.api.models.x.analysis.AnalysisClassifyParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisClassifyResponse
import com.x_twitter_scraper.api.models.x.analysis.AnalysisMarketSignalsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisMarketSignalsResponse
import com.x_twitter_scraper.api.models.x.analysis.AnalysisNewsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisNewsResponse
import com.x_twitter_scraper.api.models.x.analysis.AnalysisSentimentParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisSentimentResponse
import com.x_twitter_scraper.api.models.x.analysis.AnalysisViralScoreParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisViralScoreResponse

/** AI analysis of posts, at 2 credits per analyzed post */
interface AnalysisServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AnalysisServiceAsync

    /**
     * Answers each post's attitude, its intensity & its sarcasm probability. Send post IDs, an X
     * search, an account, a List, a post's replies or quotes, or your own texts. Each analyzed post
     * costs 2 credits. A post without an analysis costs nothing.
     */
    suspend fun sentiment(
        params: AnalysisSentimentParams = AnalysisSentimentParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisSentimentResponse

    /** @see sentiment */
    suspend fun sentiment(requestOptions: RequestOptions): AnalysisSentimentResponse =
        sentiment(AnalysisSentimentParams.none(), requestOptions)

    /**
     * Answers each post's hook, clarity, payoff, humor, spam risk & likely reaction, & adds a Viral
     * Score from 0 to 100 with a verdict. The score rates the wording. It does not predict likes or
     * views. Send drafts in texts to score them before posting. Each analyzed post costs 2 credits.
     */
    suspend fun viralScore(
        params: AnalysisViralScoreParams = AnalysisViralScoreParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisViralScoreResponse

    /** @see viralScore */
    suspend fun viralScore(requestOptions: RequestOptions): AnalysisViralScoreResponse =
        viralScore(AnalysisViralScoreParams.none(), requestOptions)

    /**
     * Answers whether each post is about your brand, the author's attitude toward it & the author's
     * relationship with it. Name the brand in analysis.targets. Each analyzed post costs 2 credits.
     */
    suspend fun brand(
        params: AnalysisBrandParams = AnalysisBrandParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisBrandResponse

    /** @see brand */
    suspend fun brand(requestOptions: RequestOptions): AnalysisBrandResponse =
        brand(AnalysisBrandParams.none(), requestOptions)

    /**
     * Answers each post's format, such as reporting, commentary or speculation, its source
     * attribution & its relevance to your topic. Name the topic in analysis.targets. Each analyzed
     * post costs 2 credits.
     */
    suspend fun news(
        params: AnalysisNewsParams = AnalysisNewsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisNewsResponse

    /** @see news */
    suspend fun news(requestOptions: RequestOptions): AnalysisNewsResponse =
        news(AnalysisNewsParams.none(), requestOptions)

    /**
     * Answers each post's stance on an asset, its content type, its conviction & its relevance. The
     * summary counts bullish & bearish posts per cashtag. Name the asset in analysis.targets. Each
     * analyzed post costs 2 credits.
     */
    suspend fun marketSignals(
        params: AnalysisMarketSignalsParams = AnalysisMarketSignalsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisMarketSignalsResponse

    /** @see marketSignals */
    suspend fun marketSignals(requestOptions: RequestOptions): AnalysisMarketSignalsResponse =
        marketSignals(AnalysisMarketSignalsParams.none(), requestOptions)

    /**
     * Answers 1 to 8 questions you write in analysis.questions. A choice picks a category, such as
     * a topic. A score picks a level. A probability answers yes or no. Without questions it answers
     * the sentiment questions. Each analyzed post costs 2 credits.
     */
    suspend fun classify(
        params: AnalysisClassifyParams = AnalysisClassifyParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AnalysisClassifyResponse

    /** @see classify */
    suspend fun classify(requestOptions: RequestOptions): AnalysisClassifyResponse =
        classify(AnalysisClassifyParams.none(), requestOptions)

    /**
     * A view of [AnalysisServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AnalysisServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/analysis/sentiment`, but is otherwise the same
         * as [AnalysisServiceAsync.sentiment].
         */
        @MustBeClosed
        suspend fun sentiment(
            params: AnalysisSentimentParams = AnalysisSentimentParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisSentimentResponse>

        /** @see sentiment */
        @MustBeClosed
        suspend fun sentiment(
            requestOptions: RequestOptions
        ): HttpResponseFor<AnalysisSentimentResponse> =
            sentiment(AnalysisSentimentParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/viral-score`, but is otherwise the same
         * as [AnalysisServiceAsync.viralScore].
         */
        @MustBeClosed
        suspend fun viralScore(
            params: AnalysisViralScoreParams = AnalysisViralScoreParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisViralScoreResponse>

        /** @see viralScore */
        @MustBeClosed
        suspend fun viralScore(
            requestOptions: RequestOptions
        ): HttpResponseFor<AnalysisViralScoreResponse> =
            viralScore(AnalysisViralScoreParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/brand`, but is otherwise the same as
         * [AnalysisServiceAsync.brand].
         */
        @MustBeClosed
        suspend fun brand(
            params: AnalysisBrandParams = AnalysisBrandParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisBrandResponse>

        /** @see brand */
        @MustBeClosed
        suspend fun brand(requestOptions: RequestOptions): HttpResponseFor<AnalysisBrandResponse> =
            brand(AnalysisBrandParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/news`, but is otherwise the same as
         * [AnalysisServiceAsync.news].
         */
        @MustBeClosed
        suspend fun news(
            params: AnalysisNewsParams = AnalysisNewsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisNewsResponse>

        /** @see news */
        @MustBeClosed
        suspend fun news(requestOptions: RequestOptions): HttpResponseFor<AnalysisNewsResponse> =
            news(AnalysisNewsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/market-signals`, but is otherwise the
         * same as [AnalysisServiceAsync.marketSignals].
         */
        @MustBeClosed
        suspend fun marketSignals(
            params: AnalysisMarketSignalsParams = AnalysisMarketSignalsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisMarketSignalsResponse>

        /** @see marketSignals */
        @MustBeClosed
        suspend fun marketSignals(
            requestOptions: RequestOptions
        ): HttpResponseFor<AnalysisMarketSignalsResponse> =
            marketSignals(AnalysisMarketSignalsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/analysis/classify`, but is otherwise the same as
         * [AnalysisServiceAsync.classify].
         */
        @MustBeClosed
        suspend fun classify(
            params: AnalysisClassifyParams = AnalysisClassifyParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AnalysisClassifyResponse>

        /** @see classify */
        @MustBeClosed
        suspend fun classify(
            requestOptions: RequestOptions
        ): HttpResponseFor<AnalysisClassifyResponse> =
            classify(AnalysisClassifyParams.none(), requestOptions)
    }
}
