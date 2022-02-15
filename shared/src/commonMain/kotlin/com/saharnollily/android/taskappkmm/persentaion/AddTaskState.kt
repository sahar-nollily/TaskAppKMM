package com.saharnollily.android.taskappkmm.persentaion

data class AddTaskState(
    var isLoading: Boolean = false,
    var error: String = "",
    var data: Boolean = false,
) {
    constructor() : this(
        isLoading = false,
        error = "",
        data = false
    )
}
