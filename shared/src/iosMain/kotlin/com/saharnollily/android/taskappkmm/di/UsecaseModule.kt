package com.saharnollily.android.taskappkmm.di

import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.domain.usecase.AddTask
import com.saharnollily.android.taskappkmm.domain.usecase.GetTasks

class UsecaseModule(
    private val taskDao: TaskDao
) {

    val getTasks: GetTasks by lazy {
        GetTasks(taskDao)
    }

    val addTask: AddTask by lazy {
        AddTask(taskDao)
    }

}