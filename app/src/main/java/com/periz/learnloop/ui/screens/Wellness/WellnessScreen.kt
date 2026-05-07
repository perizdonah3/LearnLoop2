package com.periz.learnloop.ui.screens.Wellness

import androidx.navigation.NavController
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
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_PROGRESSTRACKER
import com.periz.learnloop.navigation.ROUT_STUDYTIMER
import com.periz.learnloop.ui.theme.Pink80

data class WellnessTip(
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessScreen(navController: NavController) {

    val tips = listOf(
        WellnessTip("Focus Timer", "Use Pomodoro sessions to improve concentration.", Icons.Default.Timer),
        WellnessTip("Mindful Breaks", "Take short breaks between study sessions.", Icons.Default.SelfImprovement),
        WellnessTip("Healthy Habits", "Sleep well, hydrate, and stay active.", Icons.Default.Favorite),
        WellnessTip("Stress Relief", "Practice breathing exercises during pressure.", Icons.Default.Spa)
    )

    // ❌ Bottom navigation removed
    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(padding)
                .padding(16.dp)
        ) {

            // TOP BAR
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

            Text(
                text = "Wellness Hub ",
                fontSize = 28.sp,
                color = Color.Black
            )

            Text(
                text = "Balance your studies with your wellbeing.",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text("Daily Wellness Check", color = Color.Black)

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { 0.8f },
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        trackColor = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("You’re doing great today!", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Start Focus Session")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Wellness Tips",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tips) { tip ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                tip.icon,
                                contentDescription = null,
                                tint = Color.Black
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    tip.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    tip.description,
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessScreenPreview() {
    WellnessScreen(rememberNavController())
}