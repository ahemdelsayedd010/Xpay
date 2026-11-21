package com.example.xpay.ui.analytics

import androidx.compose.ui.graphics.Color

data class CategoryExpense(
    val categoryName: String,
    val transactionCount: Int,
    val amountCents: Long,
    val percentage: Int,
    val iconId: Int,
    val iconBgColor: Color,
    val iconTintColor: Color
)
