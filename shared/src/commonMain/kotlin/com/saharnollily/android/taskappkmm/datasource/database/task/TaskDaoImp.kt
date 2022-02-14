package com.saharnollily.android.taskappkmm.datasource.database.task

import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.android.taskappkmm.util.toTaskList
import com.saharnollily.taskapp.database.TaskDatabase

class TaskDaoImp(
    private val taskDatabase: TaskDatabase
): TaskDao {

    val queries = taskDatabase.taskDBQueries
    override fun getTasks(): List<Task> {
        return queries.selectAll().executeAsList().toTaskList()
    }

    override fun addTask(task: Task) {
        queries.insertTask(id = null, title = task.title)
    }
}