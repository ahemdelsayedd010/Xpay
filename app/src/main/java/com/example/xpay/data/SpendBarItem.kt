package com.example.xpay.data

data class SpendBarItem(
    val label: String,       // "Mon", "Tue"... or "Wk 1", "Wk 2"...
    val title: String,       // "Sun (This Week)" or "Week 1"
    val amountCents: Long,   // 15300L ($153.00)
    val heightRatio: Float   // 0.12f to 1.0f
)
