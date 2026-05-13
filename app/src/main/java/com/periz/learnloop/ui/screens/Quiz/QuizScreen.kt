package com.periz.learnloop.ui.screens.Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.R
import com.periz.learnloop.navigation.ROUT_QUIZRESULTS
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun QuizScreen(navController: NavHostController) {

    var selectedAnswer by remember { mutableStateOf("") }

    val options = listOf(
        "Hyper Text Markup Language",
        "High Transfer Machine Language",
        "Home Tool Markup Language",
        "Hyperlinks Text Machine Language"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
    ) {

        // 🌟 subtle background image (no UI change, just vibe)
        Image(
            painter = painterResource(id = R.drawable.img_22),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.08f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
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
                        navController.popBackStack()
                    },
                    tint = Color.Black
                )
            }

            Text(
                text = "Quiz Challenge",
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Test your knowledge and improve your skills",
                color = Color.Black,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 🎯 MAIN QUIZ CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "What does HTML stand for?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    options.forEach { option ->

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .background(
                                    color = if (selectedAnswer == option)
                                        Color.White.copy(alpha = 0.7f)
                                    else
                                        Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedAnswer = option
                                }
                                .padding(4.dp)
                        ) {

                            RadioButton(
                                selected = selectedAnswer == option,
                                onClick = { selectedAnswer = option },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Black
                                )
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = option,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = {
                            navController.navigate(ROUT_QUIZRESULTS)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun QuizScreenPreview() {
    QuizScreen(rememberNavController())
}