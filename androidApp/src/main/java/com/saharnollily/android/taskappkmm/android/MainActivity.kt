package com.saharnollily.android.taskappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.saharnollily.android.taskappkmm.android.persentation.SharedViewModel
import com.saharnollily.android.taskappkmm.android.persentation.add_task.AddTaskScreen
import com.saharnollily.android.taskappkmm.android.persentation.task_list.TaskListScreen
import com.saharnollily.android.taskappkmm.android.ui.theme.TaskAppKMMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppKMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: SharedViewModel = hiltViewModel()
                    Column {
                        AddTaskScreen { viewModel.addTask(it) }
                        TaskListScreen(viewModel.state.value)
                    }
                }
            }
        }
    }
}