package `in`.hahow.android_recruit_project.di

import com.hahow.common.di.commonModule
import com.hahow.data.di.dataModule
import com.hahow.data.di.servicesModule
import com.hahow.network.di.networkModule
import `in`.hahow.android_recruit_project.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::MainViewModel)
}

val appModules = listOf(
    commonModule,
    dataModule,
    viewModelsModule,
    networkModule,
    servicesModule,
)
