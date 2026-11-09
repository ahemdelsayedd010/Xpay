package com.example.xpay.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

data class Action(
    val title: String,
    @DrawableRes val iconId: Int,
    val actionDetails: String
)
