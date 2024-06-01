package `in`.hahow.android_recruit_project.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.ui.widgets.Appbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Appbar(titleRes = R.string.lab_Main_title) },
    ) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding),
        )
    }
}
