package com.saharnollily.android.taskappkmm.android.persentation.add_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.saharnollily.android.taskappkmm.android.ui.theme.Purple700
import com.saharnollily.android.taskappkmm.domain.models.Task

@Composable
fun AddTaskDialog(
    errorMessage: String,
    onAddTask: (Task) -> Unit,
) {
    var textFiledState by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .size(500.dp, 200.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMessage.isNotEmpty()){
                Text(text = "*$errorMessage", color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            OutlinedTextField(
                value = textFiledState,
                label = { Text(text = "Enter Task Title") },
                onValueChange = { textFiledState = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }), shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(Purple700),
                onClick = {
                    onAddTask(Task(null, textFiledState))
                    textFiledState = ""
                    focusManager.clearFocus()

                }) {
                Text(text = "Add Task")
            }
        }
    }
}