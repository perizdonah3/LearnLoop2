package com.periz.learnloop.ui.screens.Planner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.ui.theme.Pink80

data class PlannerTask(
    val title: String,
    val subject: String,
    val time: String,
    val completed: Boolean
)

@Composable
fun PlannerScreen(navController: NavController) {

    val tasks = listOf(
        PlannerTask("Revise Algebra", "Mathematics", "8:00 AM", true),
        PlannerTask("Physics Assignment", "Physics", "10:30 AM", false),
        PlannerTask("Coding Practice", "Computer Science", "1:00 PM", false),
        PlannerTask("Biology Notes Review", "Biology", "4:00 PM", true),
        PlannerTask("Chemistry Lab Prep", "Chemistry", "6:30 PM", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
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
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {
                Text(
                    text = "Organize your day and stay on top of your academic goals.",
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }

            // Grey progress card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(Color.LightGray, Color.LightGray)
                            ),
                            shape = RoundedCornerShape(22.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Text(
                            text = "Today's Progress",
                            color = Color.Black,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = { 0.6f },
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            trackColor = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "3 of 5 tasks completed",
                            color = Color.Black
                        )
                    }
                }
            }

            // Black button
            item {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task", tint = Color.White)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Add New Study Task", color = Color.White)
                }
            }

            item {
                Text(
                    text = "Today's Schedule",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(tasks) { task ->
                Card(
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = task.completed,
                            onCheckedChange = { },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.Black,
                                checkmarkColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = task.title,
                                color = Color.Black,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = task.subject,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = task.time,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerScreenPreview() {
    PlannerScreen(rememberNavController())
}