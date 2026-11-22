package com.example.xpay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xpay.data.local.dao.CardDao
import com.example.xpay.data.local.dao.NotificationDao
import com.example.xpay.data.local.dao.PendingOperationDao
import com.example.xpay.data.local.dao.TransactionDao
import com.example.xpay.data.local.dao.UserAccountDao
import com.example.xpay.data.local.entities.CardEntity
import com.example.xpay.data.local.entities.NotificationEntity
import com.example.xpay.data.local.entities.PendingOperationEntity
import com.example.xpay.data.local.entities.TransactionEntity
import com.example.xpay.data.local.entities.UserAccountEntity

@Database(
    entities =[
        UserAccountEntity::class,
        CardEntity::class,
        TransactionEntity::class,
        NotificationEntity::class,
        PendingOperationEntity::class
    ],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun cardDao(): CardDao
    abstract fun notificationDao(): NotificationDao
    abstract fun pendingOperationDao(): PendingOperationDao
}