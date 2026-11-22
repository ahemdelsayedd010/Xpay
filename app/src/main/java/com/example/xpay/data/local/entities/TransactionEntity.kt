package com.example.xpay.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = UserAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CardEntity::class,
            parentColumns = ["id"],
            childColumns = ["cardId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("cardId"),
        Index(value = ["accountId", "timestamp"])
    ]
)
data class TransactionEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val accountId: String,
    val cardId: String? = null,
    val category: String,
    val type: String,
    val amountCents: Long,
    val status: String,
    val merchant: String,
    val timestamp: Long = System.currentTimeMillis()
)
