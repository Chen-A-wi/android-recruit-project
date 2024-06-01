package `in`.hahow.android_recruit_project.di

import com.hahow.common.di.commonModules
import com.hahow.network.di.networkModules
import `in`.hahow.android_recruit_project.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModules = module {
    viewModelOf(::MainViewModel)
}

val appModules = listOf(
    commonModules,
    networkModules,
    viewModules,
)
