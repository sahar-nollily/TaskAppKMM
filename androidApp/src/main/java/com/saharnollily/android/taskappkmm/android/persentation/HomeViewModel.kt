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
import com.saharnollily.android.taskappkmm.persentaion.AddTaskState
import com.saharnollily.android.taskappkmm.persentaion.TaskListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addTask: AddTask,
    private val getTasks: GetTasks
) : ViewModel() {

    private val _state: MutableState<TaskListState> = mutableStateOf(TaskListState())
    private val _addState: MutableState<AddTaskState> = mutableStateOf(AddTaskState())


    val addState: State<AddTaskState>
        get() = _addState
    val state: State<TaskListState>
        get() = _state

    init {
        getTasks()
    }

    fun addTask(task: Task) {
        addTask.execute(task).onEach {dataState ->
            when {
                dataState.isLoading -> {
                    _addState.value = AddTaskState(isLoading = dataState.isLoading)
                    Log.d("SaharTest", "addTask: loading ${dataState.isLoading} ")
                }
                dataState.data != null -> {
                    _addState.value = AddTaskState(data = dataState.data ?: false)
                    appendData(task)
                    Log.d("SaharTest", "addTask: data ${dataState.data} ")

                }

                dataState.message != null -> {
                    _addState.value = AddTaskState(error = dataState.message ?: "")
                    Log.d("SaharTest", "addTask: message ${dataState.message} ")

                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getTasks() {
        getTasks.execute().onEach {dataState ->
            when {
                dataState.isLoading -> {
                    _state.value = TaskListState(isLoading = dataState.isLoading)
                    Log.d("SaharTest", "getTasks: loading ${dataState.isLoading} ")
                }
                dataState.data != null -> {
                    _state.value = TaskListState(data = dataState.data ?: emptyList())
                    Log.d("SaharTest", "getTasks: data ${dataState.data} ")
                }

                dataState.message != null -> {
                    _state.value = TaskListState(error = dataState.message ?: "")
                    Log.d("SaharTest", "getTasks: message ${dataState.message}")

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun appendData(task: Task) {
        val taskList = _state.value.data.toMutableList()
        taskList.add(task)
        _state.value = TaskListState(data = taskList)

    }

}
