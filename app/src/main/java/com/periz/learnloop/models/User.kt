package com.periz.learnloop.models

data class User(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val role: String = "student", // student, teacher, admin
    val profileImageUrl: String? = null,
    val institution: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)