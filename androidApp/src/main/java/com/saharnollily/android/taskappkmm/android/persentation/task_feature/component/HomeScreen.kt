package com.saharnollily.android.taskappkmm.android.persentation.task_feature.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.saharnollily.android.taskappkmm.android.persentation.HomeViewModel
import com.saharnollily.android.taskappkmm.android.persentation.add_task.AddTaskCard
import com.saharnollily.android.taskappkmm.android.persentation.task_list.TasksLists

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onException: (String) -> Unit) {
    val addState = viewModel.addState.value
    val getState = viewModel.state.value
    Column {
        AddTaskCard { viewModel.addTask(it) }
        TasksLists(getState.data ?: emptyList())

        if (addState.isLoading || getState.isLoading) {
            CircularLoading()
        }

        if (addState.error != "" || getState.error != "") {
            val message = if (addState.error.trim().isNotBlank())
                addState.error
            else
                getState.error
            onException(message)
        }
    }
}