package com.periz.learnloop.ui.screens.Subject

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_STUDYMATERIALS
import com.periz.learnloop.navigation.ROUT_STUDYTIMER
import com.periz.learnloop.ui.theme.Pink80

data class SubjectItem(
    val name: String,
    val progress: Float,
    val level: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen(navController: NavController) {

    val subjects = listOf(
        SubjectItem("Mathematics", 0.8f, "Advanced"),
        SubjectItem("Computer Science", 0.65f, "Intermediate"),
        SubjectItem("Physics", 0.5f, "Intermediate"),
        SubjectItem("Biology", 0.9f, "Expert"),
        SubjectItem("Chemistry", 0.7f, "Advanced")
    )

    // ❌ Bottom navigation removed
    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                // Top bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }

                    Row {
                        IconButton(onClick = { }) {

                        }
                        IconButton(onClick = { }) {

                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Subjects ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Track your learning journey and achievements",
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Top Performer: Biology at 90%",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(subjects) { subject ->
                        SubjectCard(subject)
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectCard(subject: SubjectItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        subject.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(subject.level, color = Color.Black, fontSize = 14.sp)
                }

                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = { subject.progress },
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                trackColor = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                "${(subject.progress * 100).toInt()}% completed",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectScreenPreview() {
    SubjectScreen(rememberNavController())
}