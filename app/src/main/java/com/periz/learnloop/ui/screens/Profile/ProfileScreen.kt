package com.periz.learnloop.ui.screens.Profile

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_SETTINGS
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun ProfileScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Black,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Periz Donah",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Software Developer",
            color = Color.Black,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                ProfileItem(
                    icon = Icons.Default.Email,
                    title = "Email",
                    subtitle = "perizdonah@example.com"
                )

                ProfileItem(
                    icon = Icons.Default.School,
                    title = "Institution",
                    subtitle = "LearnLoop Academy"
                )

                ProfileItem(
                    icon = Icons.Default.Edit,
                    title = "Skills",
                    subtitle = "Junior Software Developer"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔥 FIXED BUTTON (NO BLUE)
        Button(
            onClick = {
                navController.navigate(ROUT_SETTINGS)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profile", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ProfileItem(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                text = subtitle,
                color = Color.Black,
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}