package com.periz.learnloop.ui.screens.StudyMaterials

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.ui.theme.Pink80

data class StudyMaterial(
    val title: String,
    val type: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentStudyMaterialsScreen(navController: NavHostController) {

    val materials = listOf(
        StudyMaterial("Kotlin Basics Guide", "PDF", "Programming"),
        StudyMaterial("UI/UX Design Notes", "Slides", "Design"),
        StudyMaterial("Database Concepts", "PDF", "Technology"),
        StudyMaterial("Android Studio Setup", "Video", "Development"),
        StudyMaterial("Mathematics Revision", "PDF", "STEM"),
        StudyMaterial("Physics Practical Guide", "PDF", "Science")
    )

    // ✅ No Bottom Navigation Bar
    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(padding)
                .padding(20.dp)
        ) {

            // Top Bar ONLY
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { navController.popBackStack() }) {

                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Row {

                    IconButton(onClick = { }) { }

                    IconButton(onClick = { }) { }
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
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Recommended",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Download curated study packs for exams and assignments.",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(materials) { item ->
                    MaterialCard(item)
                }
            }
        }
    }
}

@Composable
fun MaterialCard(item: StudyMaterial) {

    val downloaded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = if (item.type == "Video")
                    Icons.Default.PlayCircle
                else
                    Icons.Default.PictureAsPdf,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(34.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

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

            IconButton(
                onClick = {
                    downloaded.value = !downloaded.value
                }
            ) {

                Icon(
                    Icons.Default.Download,
                    contentDescription = "Download",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyMaterialScreenPreview() {
    IntentStudyMaterialsScreen(rememberNavController())
}