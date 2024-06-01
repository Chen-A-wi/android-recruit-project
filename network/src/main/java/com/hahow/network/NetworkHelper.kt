package com.hahow.network

import com.hahow.network.dispatcher.MockWebDispatcher
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

const val MOCK_WEB_SERVER_PORT = 4007

inline fun <reified T> createService(
    okHttpClient: OkHttpClient,
): T {
    val baseUrl = "http://localhost:$MOCK_WEB_SERVER_PORT/"

    return buildRetrofit(baseUrl, okHttpClient).create(T::class.java)
}

val jsonBuilder = Json {
    ignoreUnknownKeys = true // skip unknown json key
    coerceInputValues = true // null default
    prettyPrint = true // format
}

fun buildRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(
            jsonBuilder.asConverterFactory("application/json".toMediaType()),
        )
        .build()
}

fun createMockWebServer(dispatcher: MockWebDispatcher): MockWebServer {
    val mockWebServer = MockWebServer()
    mockWebServer.dispatcher = dispatcher
    return mockWebServer
}

fun buildOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .protocols(listOf(Protocol.HTTP_1_1))
        .build()
}

fun createLogInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
