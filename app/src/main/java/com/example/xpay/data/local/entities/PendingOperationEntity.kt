package com.example.xpay.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "pending_operations")
data class PendingOperationEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val type: String,
    val payloadJson: String,
    val idempotencyKey: String,
    val status: String = "PENDING",
    val retryCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
