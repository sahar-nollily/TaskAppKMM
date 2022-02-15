package com.saharnollily.android.taskappkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.saharnollily.android.taskappkmm.android.persentation.task_feature.component.HomeScreen
import com.saharnollily.android.taskappkmm.android.ui.theme.Purple700
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
                    val isDialogOpen = remember {
                        mutableStateOf(false)
                    }
                    Scaffold(floatingActionButton = {
                        IconButton(
                            modifier = Modifier
                                .padding(bottom = 50.dp, end = 50.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(Purple700)
                                .size(60.dp),
                            onClick = {
                                isDialogOpen.value = true
                            }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White
                            )
                        }
                    }) {
                        HomeScreen( isDialogOpen = isDialogOpen)
                    }
                }
            }
        }
    }
}