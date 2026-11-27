package com.example.xpay.ui.settings

import androidx.annotation.DrawableRes

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val featureDetails: String? = null
)
