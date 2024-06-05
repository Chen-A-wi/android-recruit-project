package `in`.hahow.android_recruit_project.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidrecruitprojectTheme {
                MainScreen()
            }
        }
    }
}
