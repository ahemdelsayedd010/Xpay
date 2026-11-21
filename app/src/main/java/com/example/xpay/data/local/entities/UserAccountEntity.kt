package com.example.xpay.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_accounts")
data class UserAccountEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val balanceCents: Long,
    val maskedAccountNumber: String,
    val currency: String = "USD",
    val lastUpdateAt: Long = System.currentTimeMillis()
)
