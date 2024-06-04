package `in`.hahow.android_recruit_project.common

import android.app.Application
import `in`.hahow.android_recruit_project.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(appModules)
        }
    }
}
