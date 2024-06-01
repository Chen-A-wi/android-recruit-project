package `in`.hahow.android_recruit_project.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
        )
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    Button(modifier = modifier, onClick = { viewModel.getCourses() }) {
        Text("Check API")
    }
}
