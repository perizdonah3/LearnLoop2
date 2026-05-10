package com.periz.learnloop.ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.*
import com.periz.learnloop.ui.theme.Pink80

data class FeatureItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val features = listOf(

        FeatureItem(
            "Study Planner",
            Icons.Default.EventNote,
            ROUT_PLANNER
        ),

        FeatureItem(
            "Notes Vault",
            Icons.Default.Folder,
            ROUT_NOTEVAULT
        ),


        FeatureItem(
            "Notes",
            Icons.Default.Edit,
            ROUT_NOTESSCREEN
        ),

        FeatureItem(
            "Study Materials",
            Icons.Default.MenuBook,
            ROUT_STUDYMATERIALS
        ),

        FeatureItem(
            "Progress Tracker",
            Icons.Default.ShowChart,
            ROUT_PROGRESSTRACKER
        ),

        FeatureItem(
            "Quiz",
            Icons.Default.Quiz,
            ROUT_QUIZ
        ),

        FeatureItem(
            "Quiz Results",
            Icons.Default.Assessment,
            ROUT_QUIZRESULTS
        ),

        FeatureItem(
            "Study Timer",
            Icons.Default.Timer,
            ROUT_STUDYTIMER
        ),

        FeatureItem(
            "Subjects",
            Icons.Default.School,
            ROUT_SUBJECT
        ),

        FeatureItem(
            "Subject Details",
            Icons.Default.Info,
            ROUT_SUBJECTDETAILS
        ),

        FeatureItem(
            "Career Hub",
            Icons.Default.Work,
            ROUT_CAREERGUIDANCE
        ),

        FeatureItem(
            "Community",
            Icons.Default.Groups,
            ROUT_STUDENTCOMMUNITY
        ),

        FeatureItem(
            "Wellness",
            Icons.Default.SelfImprovement,
            ROUT_WELLNESS
        ),

        FeatureItem(
            "Achievements",
            Icons.Default.EmojiEvents,
            ROUT_ACHIEVEMENTS
        ),

        FeatureItem(
            "Study Streak",
            Icons.Default.LocalFireDepartment,
            ROUT_STUDYSTREAK
        )
    )

    Scaffold(
        containerColor = Pink80,

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = true,

                    onClick = {
                        navController.navigate(ROUT_HOME) {
                            popUpTo(ROUT_HOME) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },

                    icon = {
                        Icon(Icons.Default.Home, null)
                    },

                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,

                    onClick = {
                        navController.navigate(ROUT_NOTESSCREEN) {
                            launchSingleTop = true
                        }
                    },

                    icon = { Icon(Icons.Default.Edit, contentDescription = "Notes") },

                    label = {
                        Text("Notes")
                    }
                )

                NavigationBarItem(
                    selected = false,

                    onClick = {
                        navController.navigate(ROUT_PROFILE) {
                            launchSingleTop = true
                        }
                    },

                    icon = {
                        Icon(Icons.Default.Person, null)
                    },

                    label = {
                        Text("Profile")
                    }
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink80)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.Black,

                    modifier = Modifier.clickable {
                        navController.navigate(ROUT_SETTINGS)
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Welcome Back ",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "LearnLoop your success hub",
                color = Color.Black,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )

            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Today's Goal",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Complete 3 study tasks and track your progress",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                modifier = Modifier.weight(1f),

                horizontalArrangement = Arrangement.spacedBy(14.dp),

                verticalArrangement = Arrangement.spacedBy(14.dp)

            ) {

                items(features) { item ->

                    FeatureCard(
                        item = item,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun FeatureCard(
    item: FeatureItem,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)

            .clickable {
                navController.navigate(item.route)
            },

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()

                .clickable {
                    navController.navigate(item.route)
                }

                .padding(18.dp),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = Color.Black,
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}