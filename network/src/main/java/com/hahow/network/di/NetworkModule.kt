package com.hahow.network.di

import com.hahow.network.buildOkHttpClient
import com.hahow.network.createLogInterceptor
import com.hahow.network.dispatcher.MockWebDispatcher
import com.hahow.network.createMockWebServer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::createLogInterceptor)
    singleOf(::MockWebDispatcher)
    singleOf(::buildOkHttpClient)
    singleOf(::createMockWebServer)
}