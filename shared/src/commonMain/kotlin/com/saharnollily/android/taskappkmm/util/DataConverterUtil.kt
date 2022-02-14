package com.saharnollily.android.taskappkmm.util

import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.taskapp.database.Task_Entity

fun Task_Entity.toTask(): Task {
    return Task(
        title = title
    )
}

fun List<Task_Entity>.toTaskList(): List<Task> {
    return map {
        it.toTask()
    }
}