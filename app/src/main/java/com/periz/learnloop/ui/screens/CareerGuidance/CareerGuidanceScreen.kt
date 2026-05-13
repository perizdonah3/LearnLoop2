package com.periz.learnloop.ui.screens.CareerGuidance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

data class CareerFeature(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val imageRes: Int
)

@Composable
fun CareerHubScreen(navHostController: NavHostController) {

    val features = listOf(
        CareerFeature("Career Quiz", "Discover careers that fit you", Icons.Default.Psychology, R.drawable.img_11),
        CareerFeature("Internships", "Find student-friendly internships", Icons.Default.Work, R.drawable.img_18),
        CareerFeature("Scholarships", "Explore funding opportunities", Icons.Default.School, R.drawable.img_3),
        CareerFeature("CV Builder", "Create your professional CV", Icons.Default.Description, R.drawable.img_17),
        CareerFeature("Courses", "Get course recommendations", Icons.Default.MenuBook, R.drawable.img_23),
        CareerFeature("Trends", "See what's in demand", Icons.Default.TrendingUp, R.drawable.img_15)
    )

    Scaffold(
        containerColor = Pink80
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink80)
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

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
                        navHostController.popBackStack()
                    },
                    tint = Color.Black
                )
            }

            Text(
                text = "Career Hub ",
                fontSize = 26.sp,
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your future starts here. Explore opportunities and plan your career journey.",
                fontSize = 15.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(Color.LightGray, Color.LightGray)
                            )
                        )
                        .padding(18.dp)
                ) {
                    Column {
                        Text(
                            text = "Career Readiness Score",
                            color = Color.Black,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        LinearProgressIndicator(
                            progress = { 0.75f },
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "75% Ready",
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 12.dp)
            ) {

                items(features) { feature ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable { },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Image(
                                painter = painterResource(id = feature.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(65.dp),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Icon(
                                    imageVector = feature.icon,
                                    contentDescription = feature.title,
                                    tint = Color.Black,
                                    modifier = Modifier.size(28.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = feature.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = feature.description,
                                    fontSize = 12.sp,
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
fun CareerGuidanceScreenPreview() {
    CareerHubScreen(rememberNavController())
}