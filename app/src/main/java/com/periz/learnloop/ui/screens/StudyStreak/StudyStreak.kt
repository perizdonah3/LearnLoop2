package com.periz.learnloop.ui.screens.StudyStreak

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

        // ✅ TOP BAR
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
                    ) {


                    }
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

                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Streak",

                        tint = Color(0xFFFF6B00),

                        modifier = Modifier.size(70.dp)
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

                Text(
                    text = "Mark Today as Studied",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyStreakPreview() {
    StudyStreakScreen(rememberNavController())
}