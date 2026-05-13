package com.periz.learnloop.ui.screens.StudentCommunity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.ui.theme.Pink80

data class CommunityPost(
    val user: String,
    val topic: String,
    val message: String,
    val replies: Int,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCommunityScreen(navController: NavController) {

    val posts = listOf(
        CommunityPost("Periz", "Mathematics Help", "Can someone explain integration by parts?", 12, R.drawable.img_28),
        CommunityPost("Moses", "Computer Science", "Best resources to learn Kotlin for beginners?", 8, R.drawable.img_30),
        CommunityPost("Dorah", "Physics Discussion", "How do you solve projectile motion problems?", 15, R.drawable.img_29),
        CommunityPost("Adam", "Exam Motivation", "How do you stay focused during revision week?", 20, R.drawable.img_31)
    )

    Scaffold(
        containerColor = Pink80
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink80)
                .padding(padding)
                .padding(16.dp)
        ) {

            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }

                Row {
                    IconButton(onClick = { }) { }
                    IconButton(onClick = { }) { }
                }
            }

            Text(
                text = "Student Community ",
                color = Color.Black,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Connect, learn, and grow with fellow students.",
                fontSize = 16.sp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Top decorative card with image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(22.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_27),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text("Active Members", color = Color.Black)
                    Text("2,450+ Students", color = Color.Black, fontSize = 22.sp)
                    Text(
                        "Join discussions, study groups & support forums",
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Icon(Icons.Default.GroupAdd, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Join a Study Group", color = Color.White)
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Trending Discussions",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(posts) { post ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Image(
                                    painter = painterResource(id = post.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(45.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(Modifier.width(10.dp))

                                Column {
                                    Text(post.user, color = Color.Black)
                                    Text(post.topic, fontSize = 13.sp, color = Color.Black)
                                }
                            }

                            Spacer(Modifier.height(10.dp))

                            Text(post.message, color = Color.Black)

                            Spacer(Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.ChatBubbleOutline,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Spacer(Modifier.width(6.dp))
                                Text("${post.replies} replies", fontSize = 12.sp, color = Color.Black)
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
fun StudentCommunityScreenPreview() {
    StudentCommunityScreen(rememberNavController())
}