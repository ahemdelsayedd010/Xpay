package com.example.xpay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xpay.data.local.entities.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards ORDER BY createdAt DESC")
    fun getAllCardsFlow(): Flow<List<CardEntity>>

    @Query("UPDATE cards SET isFrozen = :isFrozen WHERE id = :cardId")
    suspend fun updateCardFreezeStatus(cardId: String, isFrozen: Boolean)

    @Query("UPDATE cards SET monthlyLimit = :limitCents WHERE id = :cardId")
    suspend fun updateMonthlyLimit(cardId: String, limitCents: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)
}
