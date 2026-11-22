package com.example.xpay

import android.app.Application
import androidx.room.Room
import com.example.xpay.data.local.AppDatabase

class XpayApp : Application() {
    val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "xpay.db")
            .fallbackToDestructiveMigration(true) // development only
            .build()
    }
}