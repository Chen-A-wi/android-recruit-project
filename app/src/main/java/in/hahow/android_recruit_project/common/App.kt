package `in`.hahow.android_recruit_project.common

import com.hahow.network.MOCK_WEB_SERVER_PORT
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.android.inject
import kotlin.concurrent.thread

class App : BaseApp() {
   private val mockServer by inject<MockWebServer>()
    override fun onCreate() {
        super.onCreate()

        thread {
            mockServer.start(MOCK_WEB_SERVER_PORT)
        }
    }
}
