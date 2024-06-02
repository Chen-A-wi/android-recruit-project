package `in`.hahow.android_recruit_project.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahow.domain.repository.CoursesRepository
import com.hahow.network.Result
import com.hahow.network.asResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CoursesRepository,
) : ViewModel() {
    fun getCourses() {
        println("============= intoFunction")

        viewModelScope.launch {
            repository.getCourses()
                .flowOn(Dispatchers.IO)
                .asResult()
                .collect {
                    when (it) {
                        is Result.Error -> {
                            println("==================Fail: ${it.exception.message}")
                        }

                        Result.Loading -> {
                            println("==================Loading")
                        }

                        is Result.Success -> {
                            println("==================Success: ${it.data}}")
                        }
                    }
                }
        }
    }
}
