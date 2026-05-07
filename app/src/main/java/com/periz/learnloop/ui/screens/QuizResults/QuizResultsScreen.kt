package com.periz.learnloop.ui.screens.QuizResults

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_QUIZ
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun QuizResultsScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() },
                tint = Color.Black
            )




        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Quiz Results ",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.4f),
                modifier = Modifier.fillMaxSize()
            ) {}

            Text(
                text = "85%",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
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

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ResultItem("Correct Answers", "17")
                ResultItem("Wrong Answers", "3")
                ResultItem("Time Taken", "12 min")
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 🔥 Black button
        Button(
            onClick = { navController.navigate(ROUT_QUIZ) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Retry", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Try Again", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🔥 Black outlined button
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
            Icon(Icons.Default.EmojiEvents, contentDescription = "Home", tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back to Home", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ResultItem(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            color = Color.Black
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuizResultsScreenPreview() {
    QuizResultsScreen(rememberNavController())
}