package com.hahow.data.di

import com.hahow.data.api.CourseApi
import com.hahow.data.repository.CoursesRepositoryImpl
import com.hahow.domain.repository.CoursesRepository
import com.hahow.network.createService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val servicesModule = module {
    single<CourseApi> { createService(get()) }
}

val dataModule = module {
    singleOf(::CoursesRepositoryImpl) { bind<CoursesRepository>() }
}