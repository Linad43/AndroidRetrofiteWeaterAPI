package com.example.androidretrofiteweaterapi.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)