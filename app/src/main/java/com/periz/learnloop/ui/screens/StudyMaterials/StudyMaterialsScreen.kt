package com.periz.learnloop.ui.screens.StudyMaterials

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

data class StudyMaterial(
    val title: String,
    val type: String,
    val category: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentStudyMaterialsScreen(navController: NavHostController) {

    val materials = listOf(
        StudyMaterial("Kotlin Basics Guide", "PDF", "Programming", R.drawable.img_13),
        StudyMaterial("UI/UX Design Notes", "Slides", "Design", R.drawable.img_14),
        StudyMaterial("Database Concepts", "PDF", "Technology", R.drawable.img_15),
        StudyMaterial("Android Studio Setup", "Video", "Development", R.drawable.img_18),
        StudyMaterial("Mathematics Revision", "PDF", "STEM", R.drawable.img_16),
        StudyMaterial("Physics Practical Guide", "PDF", "Science", R.drawable.img_17)
    )

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink80)
                .padding(padding)
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Study Materials",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Explore notes, guides, and resources for your success",
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Recommended", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(
                        "Download curated study packs for exams and assignments.",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(materials) { item ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {

                        Column {

                            // ✅ IMAGE ADDED (NO UI CHANGE)
                            Image(
                                painter = painterResource(id = item.image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                contentScale = ContentScale.Crop
                            )

                            Row(
                                modifier = Modifier
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector =
                                        if (item.type == "Video")
                                            Icons.Default.PlayCircle
                                        else
                                            Icons.Default.PictureAsPdf,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(34.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {

                                    Text(
                                        text = item.title,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )

                                    Text(
                                        text = "${item.type} • ${item.category}",
                                        color = Color.Black
                                    )
                                }

                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Download,
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
}

@Preview
@Composable
fun StudyMaterialScreenPreview() {
    IntentStudyMaterialsScreen(rememberNavController())
}