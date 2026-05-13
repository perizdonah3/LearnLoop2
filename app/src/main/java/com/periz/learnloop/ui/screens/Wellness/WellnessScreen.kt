package com.periz.learnloop.ui.screens.Wellness

import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

data class WellnessTip(
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessScreen(navController: NavController) {

    val tips = listOf(
        WellnessTip("Focus Timer", "Use Pomodoro sessions to improve concentration.", Icons.Default.Timer, R.drawable.img_5),
        WellnessTip("Mindful Breaks", "Take short breaks between study sessions.", Icons.Default.SelfImprovement, R.drawable.img_26),
        WellnessTip("Healthy Habits", "Sleep well, hydrate, and stay active.", Icons.Default.Favorite, R.drawable.img_11),
        WellnessTip("Stress Relief", "Practice breathing exercises during pressure.", Icons.Default.Spa, R.drawable.img_12)
    )

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(padding)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }

                Row {
                    IconButton(onClick = { }) {}
                    IconButton(onClick = { }) {}
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
                    .height(180.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.img_18),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
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

                            Image(
                                painter = painterResource(id = tip.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
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

                            Icon(
                                tip.icon,
                                contentDescription = null,
                                tint = Color.Black
                            )
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