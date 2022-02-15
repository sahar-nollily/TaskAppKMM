package com.saharnollily.android.taskappkmm.domain.usecase

import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.android.taskappkmm.util.CommonFlow
import com.saharnollily.android.taskappkmm.util.DataState
import com.saharnollily.android.taskappkmm.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class GetTasks(
    private val taskDao: TaskDao
) {

    fun execute(): CommonFlow<DataState<List<Task>>> = flow {
        try {
            emit(DataState.loading())
            val taskList = taskDao.getTasks()
            emit(DataState.data(data = taskList))

        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown Error"))

        }
    }.asCommonFlow()
}