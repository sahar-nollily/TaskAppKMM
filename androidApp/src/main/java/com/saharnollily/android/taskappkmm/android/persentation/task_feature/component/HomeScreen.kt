package com.saharnollily.android.taskappkmm.android.persentation.task_feature.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.saharnollily.android.taskappkmm.android.persentation.HomeViewModel
import com.saharnollily.android.taskappkmm.android.persentation.add_task.AddTaskDialog
import com.saharnollily.android.taskappkmm.android.persentation.task_list.TasksLists

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    isDialogOpen: MutableState<Boolean>,
) {
    val addState = viewModel.addState.value
    val getState = viewModel.state.value
    val errorMessage   = remember {
        mutableStateOf("")
    }
    Column {
        if (isDialogOpen.value) {
            Dialog(onDismissRequest = {
                errorMessage.value = ""
                isDialogOpen.value = false
            }) {
                AddTaskDialog(errorMessage.value) { viewModel.addTask(it) }
            }
        }

        TasksLists(getState.data)

        when {
            addState.data -> {
                isDialogOpen.value = false
                addState.data = false
                errorMessage.value = ""
            }
            addState.isLoading || getState.isLoading -> {
                CircularLoading()
            }
            addState.error != "" -> {
                errorMessage.value = addState.error
            }

        }
    }
}