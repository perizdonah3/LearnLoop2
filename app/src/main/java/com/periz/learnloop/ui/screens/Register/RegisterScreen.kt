package com.periz.learnloop.ui.screens.Register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.periz.learnloop.navigation.ROUT_LOGIN
import com.periz.learnloop.navigation.ROUT_REGISTER

// Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.periz.learnloop.ui.theme.Pink80

@Composable
fun RegisterScreen(navController: NavHostController) {

    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Create Account",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Join LearnLoop today",
            color = Color.Black,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                // 👤 Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = {
                        Text("Full Name")
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Name"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // 📧 Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text("Email")
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // 🔒 Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text("Password")
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 🚀 Register Button
                Button(
                    onClick = {

                        if (isLoading) return@Button

                        if (
                            fullName.isBlank() ||
                            email.isBlank() ||
                            password.isBlank()
                        ) {

                            Toast.makeText(
                                context,
                                "All fields are required",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        isLoading = true

                        auth.createUserWithEmailAndPassword(
                            email.trim(),
                            password.trim()
                        ).addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                val userId = auth.currentUser?.uid

                                if (userId != null) {

                                    val userMap = mapOf(
                                        "uid" to userId,
                                        "fullName" to fullName,
                                        "email" to email
                                    )

                                    database.child("Users")
                                        .child(userId)
                                        .setValue(userMap)
                                        .addOnCompleteListener {

                                            isLoading = false

                                            Toast.makeText(
                                                context,
                                                "Account Created 🎉",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            navController.navigate(ROUT_HOME) {
                                                popUpTo(ROUT_REGISTER) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                }

                            } else {

                                isLoading = false

                                Toast.makeText(
                                    context,
                                    task.exception?.message
                                        ?: "Registration failed",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {

                    Text(
                        text = if (isLoading)
                            "Creating..."
                        else
                            "Register",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 🔁 Go to Login
                TextButton(
                    onClick = {
                        navController.navigate(ROUT_LOGIN)
                    },
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                ) {

                    Text(
                        text = "Already have an account? Login",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}