package com.example.xpay.ui.cards

import androidx.compose.ui.graphics.Color

data class Card(
    val brandId: Int,
    val holderName: String,
    val cardNumber: String,
    val availableBalance: Long,
    val expiryMonth: Int,
    val expiryYear: Int,
    val cardType: String,
    val cardBg: Color? = Color.Black ,
)
