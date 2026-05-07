package com.periz.learnloop.ui.screens.StudyTimer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.ui.theme.Pink80
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyTimerScreen(navController: NavController) {

    var timeLeft by remember { mutableStateOf(1500) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val progress = timeLeft / 1500f

    // ✅ No Bottom Navigation Bar
    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    // ✅ pushed top navigation upward
                    .padding(top = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // ✅ TOP NAVIGATION ONLY
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
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

                Spacer(modifier = Modifier.height(6.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(28.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            Icons.Default.Timer,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Focus Session",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CircularProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.size(160.dp),
                            strokeWidth = 10.dp,
                            color = Color.Black,
                            trackColor = Color.White
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "%02d:%02d".format(minutes, seconds),
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            FloatingActionButton(
                                onClick = {
                                    isRunning = !isRunning
                                },
                                containerColor = Color.Black
                            ) {

                                Icon(
                                    if (isRunning)
                                        Icons.Default.Pause
                                    else
                                        Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }

                            FloatingActionButton(
                                onClick = {
                                    isRunning = false
                                    timeLeft = 1500
                                },
                                containerColor = Color.White
                            ) {

                                Icon(
                                    Icons.Default.Refresh,
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
}

@Preview(showBackground = true)
@Composable
fun StudyTimerScreenPreview() {
    StudyTimerScreen(rememberNavController())
}