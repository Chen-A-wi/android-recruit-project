package `in`.hahow.android_recruit_project.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hahow.data.local.ItemData
import `in`.hahow.android_recruit_project.ui.widgets.ItemView

@Composable
fun CoursesList(
    modifier: Modifier,
    courses: List<ItemData>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(courses) { course ->
            ItemView(course = course)
        }
    }
}
