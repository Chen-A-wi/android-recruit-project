package com.hahow.common.di

import com.hahow.common.utils.AssetsReader
import com.hahow.common.utils.AssetsReaderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::AssetsReaderImpl) { bind<AssetsReader>() }
}