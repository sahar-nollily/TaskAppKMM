package com.saharnollily.android.taskappkmm.domain.usecase

import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.android.taskappkmm.util.CommonFlow
import com.saharnollily.android.taskappkmm.util.DataState
import com.saharnollily.android.taskappkmm.util.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddTask(
    private val taskDao: TaskDao
) {

    fun execute(task: Task): CommonFlow<DataState<Boolean>> = flow {
        try {
            emit(DataState.loading())

            if(task.title.isBlank()){
                emit(DataState.error(message = "Title Field Required"))
            }else{
                taskDao.addTask(task)
                emit(DataState.data(data = true))
            }

        }catch (e: Exception){
            if(e.message?.contains("SQLITE_CONSTRAINT_UNIQUE") == true || e.message?.contains("Sqlite operation failure") == true)
                emit(DataState.error(message = "Task Already Exist"))
            else
                emit(DataState.error(message = "Unknown Error"))

        }
    }.asCommonFlow()
}