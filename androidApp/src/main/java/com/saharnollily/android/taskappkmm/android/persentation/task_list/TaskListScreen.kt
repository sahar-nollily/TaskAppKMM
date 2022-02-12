package com.saharnollily.android.taskappkmm.android.persentation.task_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.saharnollily.android.taskappkmm.domain.models.Task
import com.saharnollily.android.taskappkmm.persentaion.TaskListState

@Composable
fun TaskListScreen(state: TaskListState) {

    when{
        state.data.isNotEmpty() -> OnSuccess(data = state.data)
        state.error.isNotBlank() -> OnError(message = state.error )
        state.isLoading -> OnLoading()
    }

}

@Composable
fun OnSuccess(data: List<Task>){
    LazyColumn(){
        itemsIndexed(data){index: Int, item: Task ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 5.dp, horizontal = 20.dp),
                shape = RoundedCornerShape(5.dp),
                elevation = 5.dp,
                backgroundColor = Color.LightGray
            ) {
                Text(text = item.title, modifier = Modifier.padding(20.dp))

            }
        }


    }
}

@Composable
fun OnLoading(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun OnError(message: String){
    Text(text = message, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}