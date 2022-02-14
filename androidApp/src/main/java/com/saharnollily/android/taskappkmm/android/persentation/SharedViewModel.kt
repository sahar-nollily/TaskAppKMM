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
class SharedViewModel @Inject constructor(
    private val addTask: AddTask,
    private val getTasks: GetTasks
): ViewModel(){

    private val _addState: MutableState<AddTaskState> = mutableStateOf(AddTaskState())
    private val _state: MutableState<TaskListState> = mutableStateOf(TaskListState())


    val addState: State<AddTaskState>
        get() = _addState
    val state: State<TaskListState>
        get() = _state

    init {
        getTasks()
    }

    fun addTask(task: Task){
        addTask.execute(task).onEach {
            when{
                it.isLoading -> {
                    _addState.value.isLoading = it.isLoading
                    Log.d("SaharTest", "addTask: loading ${it.isLoading} ")

                }
                it.data != null -> {
                    it.data?.let {
                        _addState.value.data = it
                        appendData(task)
                    }
                    Log.d("SaharTest", "addTask: data ${it.data} ")

                }

                it.message != null -> {
                    it.message?.let {
                        _addState.value.error = it
                    }
                    Log.d("SaharTest", "addTask: message ${it.message} ")

                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getTasks(){
        getTasks.execute().onEach {
            when{
                it.isLoading -> {
                    _state.value.isLoading = it.isLoading
                    Log.d("SaharTest", "getTasks: loading ${it.isLoading} ")


                }
                it.data != null -> {
                    it.data?.let {
                        _state.value.data = it
                        Log.d("SaharTest", "getTasks: data ${it} ")

                    }
                }

                it.message != null -> {
                    it.message?.let {
                        _state.value.error = it
                    }
                    Log.d("SaharTest", "getTasks: message ${it.message}")

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun appendData(task: Task){
        val taskList = ArrayList(state.value.data)
        taskList.add(task)
        _state.value = state.value.copy(data = taskList)

    }

}
