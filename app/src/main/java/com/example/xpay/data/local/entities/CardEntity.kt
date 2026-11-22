package com.example.xpay.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "cards",
    foreignKeys = [
        ForeignKey(
            entity = UserAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],indices = [Index("accountId")]
)
data class CardEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val accountId: String ,
    val brand: String,
    val type: String,
    val last4: String,
    val holderName: String,
    val expireMonth: Int,
    val expireYear: Int,
    val isFrozen: Boolean,
    val monthlyLimitCents: Long,
    val createdAt: Long = System.currentTimeMillis()
)
