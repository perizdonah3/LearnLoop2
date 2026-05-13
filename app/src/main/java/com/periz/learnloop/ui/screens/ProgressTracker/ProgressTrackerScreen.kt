package com.periz.learnloop.ui.screens.ProgressTracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun ProgressTrackerScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
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

        Text(
            text = "Progress Tracker",
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Track your academic growth and consistency",
            color = Color.Black,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 🌟 MAIN CARD (with subtle image background feel)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Box {

                // faint decorative image (NO UI CHANGE, just background feel)
                Image(
                    painter = painterResource(id = R.drawable.img_14),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0.15f),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "82%",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Overall Performance",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        ProgressCard(Icons.Default.CheckCircle, "Tasks Completed", "41", R.drawable.img_20)
        Spacer(modifier = Modifier.height(12.dp))

        ProgressCard(Icons.Default.Timer, "Study Hours", "56 hrs", R.drawable.img_21)
        Spacer(modifier = Modifier.height(12.dp))

        ProgressCard(Icons.Default.BarChart, "Quiz Average", "91%", R.drawable.img_12)
        Spacer(modifier = Modifier.height(12.dp))

        ProgressCard(Icons.Default.TrendingUp, "Weekly Growth", "+12%", R.drawable.img_10)

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ProgressCard(
    icon: ImageVector,
    title: String,
    value: String,
    imageRes: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 📊 SMALL IMAGE ADDED (keeps UI same, just visual upgrade)
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.Black,
                modifier = Modifier.size(26.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = title,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black
            )

            Text(
                text = value,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressTrackerScreenPreview() {
    ProgressTrackerScreen(rememberNavController())
}