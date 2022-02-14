package com.saharnollily.android.taskappkmm.persentaion



data class TaskState<T>(
    var isLoading: Boolean = false,
    var error: String = "",
    var data: T? = null,
){
    constructor() : this(
        isLoading = false,
        error = "",
        data = null
    )
}