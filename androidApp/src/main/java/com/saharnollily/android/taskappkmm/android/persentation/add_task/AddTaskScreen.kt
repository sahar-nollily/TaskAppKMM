package com.saharnollily.android.taskappkmm.android.persentation.add_task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saharnollily.android.taskappkmm.android.R
import com.saharnollily.android.taskappkmm.domain.models.Task

@Composable
fun AddTaskScreen(
    onAddTask: (Task) -> Unit,
) {
    var textFiledState by remember {
        mutableStateOf("")
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField( value = textFiledState,
            label = { Text(text = "Enter Task Title") },
            onValueChange = { textFiledState = it },
            singleLine = true,
            modifier = Modifier
                .weight(0.75f)
                .height(70.dp))


        Button(modifier = Modifier
            .weight(0.25f)
            .height(70.dp)
            .padding(10.dp),
            onClick = {
                onAddTask(Task(null,textFiledState))
                textFiledState = ""
            }) {
            
            Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add")

        }
    }


}