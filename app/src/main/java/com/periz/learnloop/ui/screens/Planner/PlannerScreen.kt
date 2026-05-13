package com.periz.learnloop.ui.screens.Planner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

data class PlannerTask(
    val title: String,
    val subject: String,
    val time: String,
    val image: Int,
    var completed: Boolean
)

@Composable
fun PlannerScreen(navController: NavController) {

    val tasks = remember {
        mutableStateListOf(
            PlannerTask("Revise Calculus", "Mathematics", "8:00 AM", R.drawable.img_4, true),
            PlannerTask("Physics Concepts", "Physics", "10:30 AM", R.drawable.img_5, false),
            PlannerTask("Coding Practice", "Computer Science", "1:00 PM", R.drawable.img_6, false),
            PlannerTask("Biology Revision", "Biology", "4:00 PM", R.drawable.img_7, true),
            PlannerTask("Chemistry Experiments", "Chemistry", "6:30 PM", R.drawable.img_8, false)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .systemBarsPadding()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                },
                tint = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            item {
                Text(
                    text = "Study Planner",
                    color = Color.Black,
                    fontSize = 28.sp
                )
            }

            item {
                Text(
                    text = "Organize your day and stay on top of your academic goals.",
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(22.dp))
                        .padding(20.dp)
                ) {
                    Column {
                        Text("Today's Progress", color = Color.Black)

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = { 0.6f },
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("3 of 5 tasks completed", color = Color.Black)
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        navController.navigate("add_task") // ✅ ROUTE HOOK
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Add New Study Task", color = Color.White)
                }
            }

            item {
                Text(
                    text = "Today's Schedule",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }

            items(tasks) { task ->

                Card(
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Column {

                        // ✅ IMAGE ADDED (no UI change, just inside card)
                        Image(
                            painter = painterResource(id = task.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Checkbox(
                                checked = task.completed,
                                onCheckedChange = {
                                    task.completed = it
                                }
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(task.title, color = Color.Black)
                                Text(task.subject, color = Color.Black, fontSize = 14.sp)
                            }

                            Text(task.time, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PlannerPreview() {
    PlannerScreen(rememberNavController())
}