package com.saharnollily.android.taskappkmm.android.persentation.task_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saharnollily.android.taskappkmm.android.R
import com.saharnollily.android.taskappkmm.domain.models.Task

@Composable
fun TasksLists(data: List<Task>) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (data.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(id = R.drawable.no_data),
                    contentDescription = "no data"
                )
            }
        } else {
            LazyColumn(Modifier.padding(10.dp)) {
                itemsIndexed(data) { index: Int, item: Task ->
                    Box(
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray.copy(.2f))
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = item.title)

                    }
                }
            }
        }
    }
}
