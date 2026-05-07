package com.periz.learnloop.ui.screens.SubjectDetails

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.ui.theme.Pink80

data class TopicItem(
    val title: String,
    val duration: String,
    val difficulty: String
)

@Composable
fun SubjectDetailsScreen(navController: NavHostController) {

    val topics = listOf(
        TopicItem("Introduction to Kotlin", "20 mins", "Beginner"),
        TopicItem("Variables and Data Types", "35 mins", "Beginner"),
        TopicItem("Functions in Kotlin", "40 mins", "Intermediate"),
        TopicItem("Jetpack Compose Basics", "50 mins", "Advanced"),
        TopicItem("Navigation in Apps", "30 mins", "Intermediate")
    )

    // ❌ Bottom navigation removed
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .navigationBarsPadding()
            .padding(20.dp)
    ) {

        // TOP BAR
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Row {
                IconButton(onClick = { }) {

                }

                IconButton(onClick = { }) {

                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Subject Details ",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Explore all learning topics and master new skills",
            color = Color.Black,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Progress",
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "5 Topics Available • Keep Learning!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(topics) { topic ->
                TopicCard(topic)
            }
        }
    }
}

@Composable
fun TopicCard(topic: TopicItem) {

    var bookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.PlayCircle,
                contentDescription = topic.title,
                tint = Color.Black,
                modifier = Modifier.size(38.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topic.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )

                Text(
                    text = "${topic.duration} • ${topic.difficulty}",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            IconButton(onClick = {
                bookmarked = !bookmarked
            }) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Save",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectDetailsScreenPreview() {
    SubjectDetailsScreen(rememberNavController())
}