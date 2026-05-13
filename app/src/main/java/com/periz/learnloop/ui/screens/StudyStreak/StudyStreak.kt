package com.periz.learnloop.ui.screens.StudyStreak

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.navigation.*
import com.periz.learnloop.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyStreakScreen(navController: NavController) {

    var streakDays by rememberSaveable {
        mutableStateOf(7)
    }

    Scaffold(
        containerColor = Pink80,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = " Streak",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },

                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(ROUT_HOME)
                        }
                    ) { }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink80
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink80)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Study Streak",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Decorative image background
                    Image(
                        painter = painterResource(id = R.drawable.img_14),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .alpha(0.18f),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.img_18),
                            contentDescription = "Streak",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "$streakDays Day Streak!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Keep studying daily to grow your streak ",
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    streakDays++
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Mark Today as Studied",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.img_12),
                        contentDescription = null,
                        modifier = Modifier.size(55.dp)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Consistency Matters",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "Study every day to unlock achievements",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyStreakPreview() {
    StudyStreakScreen(rememberNavController())
}