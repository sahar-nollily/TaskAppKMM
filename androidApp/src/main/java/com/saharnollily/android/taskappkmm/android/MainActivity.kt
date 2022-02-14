package com.saharnollily.android.taskappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import com.saharnollily.android.taskappkmm.android.persentation.task_feature.component.HomeScreen
import com.saharnollily.android.taskappkmm.android.ui.theme.TaskAppKMMTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppKMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    Scaffold(scaffoldState = scaffoldState) {
                        HomeScreen(onException = { message ->
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = message)
                            }
                        })
                    }
                }
            }
        }
    }
}