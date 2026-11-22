package com.example.xpay

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.xpay.data.local.AppDatabase
import com.example.xpay.data.local.DatabaseSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class XpayApp : Application() {
    val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "xpay.db")
            .fallbackToDestructiveMigration(true) // development only
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseSeeder.seedIfEmpty(database)
        }
    }
}