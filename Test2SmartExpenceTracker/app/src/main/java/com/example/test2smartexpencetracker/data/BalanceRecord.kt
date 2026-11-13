package com.example.test2smartexpencetracker.data

data class BalanceRecord(
    val id: Long = 0,
    val balance: Double,
    val timestamp: String,
    val note: String = ""
)
