package `in`.hahow.android_recruit_project.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahow.data.local.ItemData
import com.hahow.data.local.beItemViewData
import com.hahow.domain.repository.CoursesRepository
import com.hahow.network.Result
import com.hahow.network.asResult
import `in`.hahow.android_recruit_project.common.HAHOW_DEBUG_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CoursesRepository,
) : ViewModel() {
    var courses by mutableStateOf<List<ItemData>>(listOf())

    init {
        initCourseData()
    }

    private fun initCourseData() {
        viewModelScope.launch {
            repository.getCourses()
                .flowOn(Dispatchers.IO)
                .asResult()
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            Log.d(HAHOW_DEBUG_TAG, result.exception.message.toString())
                        }

                        Result.Loading -> {
                            Log.d(HAHOW_DEBUG_TAG, "In Loading")
                        }

                        is Result.Success -> {
                            Log.d(HAHOW_DEBUG_TAG, "Success")
                            courses = result.data.beItemViewData()
                        }
                    }
                }
        }
    }
}
