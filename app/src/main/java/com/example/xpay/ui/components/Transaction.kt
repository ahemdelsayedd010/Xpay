package com.example.xpay.ui.components

import androidx.annotation.DrawableRes

data class Transaction(
    val id: String,
    @DrawableRes val iconId: Int,
    val category: String = "Received",
    val title: String,
    val amountCents: Long,
    val date: String,
    val isIncoming: Boolean = true,
    val status: Boolean = false
)
