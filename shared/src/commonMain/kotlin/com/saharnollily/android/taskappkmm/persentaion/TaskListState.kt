package com.saharnollily.android.taskappkmm.persentaion

import com.saharnollily.android.taskappkmm.domain.models.Task


data class TaskListState(
    var isLoading: Boolean = false,
    var error: String = "",
    var data: List<Task> = emptyList(),
){
    constructor() : this(
        isLoading = false,
        error = "",
        data = emptyList()
    )
}

data class AddTaskState(
    var isLoading: Boolean = false,
    var error: String = "",
    var data: Boolean = false,
){
    constructor() : this(
        isLoading = false,
        error = "",
        data = false
    )
}
