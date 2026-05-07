package com.Data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.periz.learnloop.models.User
import com.periz.learnloop.navigation.*

class AuthRepository(
    private val navController: NavController,
    private val context: Context
) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(username: String, email: String, password: String, confirmPassword: String) {

        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            toast("All fields are required")
            return
        }

        if (password != confirmPassword) {
            toast("Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    toast(task.exception?.message ?: "Signup failed")
                    return@addOnCompleteListener
                }

                val uid = auth.currentUser?.uid ?: return@addOnCompleteListener

                val user = User(
                    uid = uid,
                    username = username,
                    email = email,
                    role = "student"
                )

                FirebaseDatabase.getInstance()
                    .getReference("Users")
                    .child(uid)
                    .setValue(user)
                    .addOnSuccessListener {
                        toast("Account created successfully 🎉")
                        navController.navigate(ROUT_HOME) {
                            popUpTo(ROUT_REGISTER) { inclusive = true }
                        }
                    }
                    .addOnFailureListener {
                        toast(it.message ?: "Database error")
                    }
            }
    }

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            toast("Email and password required")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    toast(task.exception?.message ?: "Login failed")
                    return@addOnCompleteListener
                }

                val uid = auth.currentUser?.uid ?: return@addOnCompleteListener

                FirebaseDatabase.getInstance()
                    .getReference("Users")
                    .child(uid)
                    .get()
                    .addOnSuccessListener { snapshot ->

                        val role = snapshot.child("role").value?.toString() ?: "student"

                        toast("Welcome back 👋")

                        if (role == "admin") {
                            navController.navigate(ROUT_HOME)
                        } else {
                            navController.navigate(ROUT_HOME)
                        }
                    }
                    .addOnFailureListener {
                        toast("Failed to load user")
                    }
            }
    }

    fun logout() {
        auth.signOut()
        navController.navigate(ROUT_HOME) {
            popUpTo(0)
        }
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    private fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}