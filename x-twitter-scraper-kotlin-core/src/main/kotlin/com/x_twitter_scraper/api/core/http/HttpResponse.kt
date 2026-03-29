// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.core.http

import java.io.InputStream

interface HttpResponse : AutoCloseable {

    fun statusCode(): Int

    fun headers(): Headers

    fun body(): InputStream

    interface Handler<T> {

        fun handle(response: HttpResponse): T
    }
}
