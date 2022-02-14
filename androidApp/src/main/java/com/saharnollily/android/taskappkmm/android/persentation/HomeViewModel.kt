package com.saharnollily.android.taskappkmm.android.persentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.android.taskappkmm.domain.usecase.AddTask
import com.saharnollily.android.taskappkmm.domain.usecase.GetTasks
import com.saharnollily.android.taskappkmm.persentaion.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addTask: AddTask,
    private val getTasks: GetTasks
) : ViewModel() {

    private val _state: MutableState<TaskState<List<Task>>> = mutableStateOf(TaskState())
    private val _addState: MutableState<TaskState<Boolean>> = mutableStateOf(TaskState())


    val addState: State<TaskState<Boolean>>
        get() = _addState
    val state: State<TaskState<List<Task>>>
        get() = _state

    init {
        getTasks()
    }

    fun addTask(task: Task) {
        addTask.execute(task).onEach {dataState ->
            when {
                dataState.isLoading -> {
                    _addState.value = TaskState(isLoading = dataState.isLoading)
                    Log.d("SaharTest", "addTask: loading ${dataState.isLoading} ")
                }
                dataState.data != null -> {
                    _addState.value = TaskState(data = dataState.data ?: false)
                    appendData(task)
                    Log.d("SaharTest", "addTask: data ${dataState.data} ")

                }

                dataState.message != null -> {
                    _addState.value = TaskState(error = dataState.message ?: "")
                    Log.d("SaharTest", "addTask: message ${dataState.message} ")

                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getTasks() {
        getTasks.execute().onEach {dataState ->
            when {
                dataState.isLoading -> {
                    _state.value = TaskState(isLoading = dataState.isLoading)
                    Log.d("SaharTest", "getTasks: loading ${dataState.isLoading} ")
                }
                dataState.data != null -> {
                    _state.value = TaskState(data = dataState.data ?: emptyList())
                    Log.d("SaharTest", "getTasks: data ${dataState.data} ")
                }

                dataState.message != null -> {
                    _state.value = TaskState(error = dataState.message ?: "")
                    Log.d("SaharTest", "getTasks: message ${dataState.message}")

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun appendData(task: Task) {
        val taskList = _state.value.data?.toMutableList() ?: mutableListOf()
        taskList.add(task)
        _state.value = TaskState(data = taskList)

    }

}
