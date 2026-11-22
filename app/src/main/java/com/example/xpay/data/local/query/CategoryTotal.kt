package com.example.xpay.data.local.query

import com.example.xpay.data.model.Category

data class CategoryTotal(
    val category: Category,
    val transactionCount: Int,
    val totalCents: Long
)
