package com.periz.learnloop.ui.screens.NotesVault

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

data class NoteItem(
    val title: String,
    val subject: String,
    val date: String,
    val icon: ImageVector,
    val image: Int
)

@Composable
fun NoteVaultScreen(navController: NavController) {

    val notes = listOf(
        NoteItem("Biology Revision", "Biology", "12 Apr 2026", Icons.Default.Science, R.drawable.img_9),
        NoteItem("Programming Basics", "Computer Science", "15 Apr 2026", Icons.Default.Code, R.drawable.img_10),
        NoteItem("Business Finance", "Business Studies", "18 Apr 2026", Icons.Default.AccountBalance, R.drawable.img_11),
        NoteItem("Chemistry Formulas", "Chemistry", "20 Apr 2026", Icons.Default.Calculate, R.drawable.img_12),
        NoteItem("Algebra & Calculus", "Mathematics", "22 Apr 2026", Icons.Default.Functions, R.drawable.img_13),
        NoteItem("Data Structures", "Computer Science", "24 Apr 2026", Icons.Default.Memory, R.drawable.img_14),
        NoteItem("Mechanics & Motion", "Physics", "26 Apr 2026", Icons.Default.Science, R.drawable.img_15)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .systemBarsPadding()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable { navController.popBackStack() },
                        tint = Color.Black
                    )
                }
            }

            item {
                Text(
                    text = "Notes Vault",
                    fontSize = 28.sp,
                    color = Color.Black
                )
            }

            item {
                Text(
                    text = "Store, organize, and access all your study materials in one place.",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(20.dp))
                        .padding(20.dp)
                ) {
                    Column {

                        Text("Storage Used", color = Color.Black)

                        Spacer(modifier = Modifier.height(10.dp))

                        LinearProgressIndicator(
                            progress = { 0.55f },
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            trackColor = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("5.5 GB of 10 GB", color = Color.Black)
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Icon(Icons.Default.UploadFile, null, tint = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Upload", color = Color.White)
                    }

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(Icons.Default.Create, null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("New Note", color = Color.Black)
                    }
                }
            }

            item {
                Text(
                    text = "Recent Notes",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }

            items(notes) { note ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // ✅ IMAGE ADDED (NO UI CHANGE)
                        Image(
                            painter = painterResource(id = note.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 10.dp),
                            contentScale = ContentScale.Crop
                        )

                        Icon(
                            note.icon,
                            contentDescription = note.title,
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(note.title, color = Color.Black)
                            Text(note.subject, color = Color.Black, fontSize = 14.sp)
                        }

                        Text(note.date, color = Color.Black, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NoteVaultPreview() {
    NoteVaultScreen(rememberNavController())
}