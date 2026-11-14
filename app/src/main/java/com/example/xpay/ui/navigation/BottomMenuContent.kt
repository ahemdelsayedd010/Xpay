package com.example.xpay.ui.navigation

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int,
    @DrawableRes val selectedIconId: Int = iconId
)
