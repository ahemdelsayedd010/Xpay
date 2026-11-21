package com.example.xpay.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
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
    ]
)
data class CardEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val accountId: String = "default_account_id",
    val brand: String = "MASTERCARD",
    val type: String = "Physical",
    val last4: String = "5897",
    val holderName: String = "Ahmed Elsayed",
    val expireMonth: Int = 5,
    val expireYear: Int = 2029,
    val isFrozen: Boolean = false,
    val monthlyLimit: Long = 1524500L,
    val createdAt: Long = System.currentTimeMillis()
)
