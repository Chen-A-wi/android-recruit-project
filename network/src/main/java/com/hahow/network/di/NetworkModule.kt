package com.hahow.network.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.hahow.network.dispatcher.MockWebDispatcher

val networkModule = module {
    singleOf(::MockWebDispatcher)
    // TODO: OkhttpClient & retrofit
}