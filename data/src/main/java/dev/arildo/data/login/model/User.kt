package dev.arildo.data.login.model

class User(
    val name: String = "",
    val photo: String = "",
    val description: String = "",
    val email: String = "",
    val token: String? = null
)