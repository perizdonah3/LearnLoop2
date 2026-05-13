package com.periz.learnloop.ui.screens.QuizResults

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_QUIZ
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun QuizResultsScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
    ) {

        // 🌟 background image (adds vibe, no UI change)
        Image(
            painter = painterResource(id = R.drawable.img_26),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.08f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Quiz Results",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 🎯 SCORE CIRCLE WITH ICON
            Box(
                modifier = Modifier.size(110.dp),
                contentAlignment = Alignment.Center
            ) {

                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxSize()
                ) {}

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        text = "85%",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Great Job!",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "You scored 17 out of 20",
                color = Color.Black,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 📊 RESULTS CARD (with subtle image feel)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {

                Box {

                    Image(
                        painter = painterResource(id = R.drawable.img_17),
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0.12f),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ResultItem("Correct Answers", "17", R.drawable.img_24)
                        ResultItem("Wrong Answers", "3", R.drawable.img_25)
                        ResultItem("Time Taken", "12 min", R.drawable.img_26)
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { navController.navigate(ROUT_QUIZ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Try Again", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { navController.navigate(ROUT_HOME) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                )
            ) {
                Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Back to Home", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ResultItem(title: String, value: String, imageRes: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                color = Color.Black
            )
        }

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Composable
fun QuizResultsScreenPreview() {
    QuizResultsScreen(rememberNavController())
}