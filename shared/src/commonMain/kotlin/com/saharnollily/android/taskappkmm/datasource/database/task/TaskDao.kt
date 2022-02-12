package com.saharnollily.android.taskappkmm.datasource.database.task

import com.saharnollily.android.taskappkmm.domain.models.Task

interface TaskDao {

    fun getTasks(): List<Task>

    fun addTask(task: Task)

}