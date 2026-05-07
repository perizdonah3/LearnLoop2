package com.periz.learnloop.ui.screens.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_LOGIN
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun SettingsScreen(navController: NavController) {

    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var studyReminders by remember { mutableStateOf(true) }

    val textColor = if (darkModeEnabled) Color.White else Color.Black
    val bgColor = if (darkModeEnabled) Color.Black else Color.White
    val cardColor = if (darkModeEnabled) Color(0xFF1A1A1A) else Color.White
    val accentGray = if (darkModeEnabled) Color.LightGray else Color.DarkGray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // 🔝 Top Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { navController.navigate(ROUT_HOME) },
                    tint = textColor
                )

                IconButton(onClick = {
                    navController.navigate(ROUT_LOGIN)
                }) {

                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Settings ",
                color = textColor,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Customize your learning experience",
                color = textColor.copy(alpha = 0.7f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // ⚙️ Main Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    SettingItem(Icons.Default.Person, "Account", darkModeEnabled)
                    SettingItem(Icons.Default.Security, "Privacy", darkModeEnabled)

                    ToggleItem(
                        icon = Icons.Default.Notifications,
                        title = "Notifications",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        darkMode = darkModeEnabled
                    )

                    ToggleItem(
                        icon = Icons.Default.DarkMode,
                        title = "Dark Mode",
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it },
                        darkMode = darkModeEnabled
                    )

                    ToggleItem(
                        icon = Icons.Default.Language,
                        title = "Study Reminders",
                        checked = studyReminders,
                        onCheckedChange = { studyReminders = it },
                        darkMode = darkModeEnabled
                    )

                    SettingItem(Icons.Default.Info, "About App", darkModeEnabled)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 💎 Premium Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (darkModeEnabled) Color(0xFF1A1A1A) else Color.LightGray
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "✨ Premium Feature",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = textColor
                    )

                    Text(
                        text = "Unlock AI-powered study tips and personalized schedules.",
                        fontSize = 14.sp,
                        color = accentGray
                    )
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    darkMode: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = if (darkMode) Color.White else Color.Black,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (darkMode) Color.White else Color.Black
        )
    }
}

@Composable
fun ToggleItem(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    darkMode: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = if (darkMode) Color.White else Color.Black,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = if (darkMode) Color.White else Color.Black
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Black,
                checkedTrackColor = Color.Gray,
                uncheckedThumbColor = Color.DarkGray,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingsScreen(rememberNavController())
}