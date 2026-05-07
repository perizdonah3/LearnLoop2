package com.periz.learnloop.ui.screens.Login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.periz.learnloop.navigation.ROUT_HOME
import com.periz.learnloop.navigation.ROUT_REGISTER

// 🔥 Firebase
import com.google.firebase.auth.FirebaseAuth
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink80)
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // 🔝 Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
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
                text = "Welcome Back",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Login to continue learning",
                color = Color.Black,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(22.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    // 📧 Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(
                                text = "Email",
                                color = Color.Black
                            )
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 🔒 Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = "Password",
                                color = Color.Black
                            )
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Password",
                                tint = Color.Black
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 🚀 LOGIN BUTTON
                    Button(
                        onClick = {

                            if (isLoading) return@Button

                            if (email.isBlank() || password.isBlank()) {
                                Toast.makeText(
                                    context,
                                    "All fields are required",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            isLoading = true

                            auth.signInWithEmailAndPassword(
                                email.trim(),
                                password.trim()
                            ).addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    isLoading = false

                                    Toast.makeText(
                                        context,
                                        "Login Successful 🎉",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(ROUT_HOME) {
                                        popUpTo(0)
                                    }

                                } else {

                                    isLoading = false

                                    Toast.makeText(
                                        context,
                                        task.exception?.message ?: "Login failed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                        },
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        )
                    ) {

                        Text(
                            text = if (isLoading)
                                "Logging in..."
                            else
                                "Login",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 🔁 Go to Register
                    TextButton(
                        onClick = {
                            navController.navigate(ROUT_REGISTER)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {

                        Text(
                            text = "Don’t have an account? Register",
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}