package com.example.xpay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xpay.data.local.entities.UserAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAccountDao {
    @Query("SELECT * FROM user_accounts LIMIT 1")
    fun getUserAccountFlow(): Flow<UserAccountEntity?>

    @Query("SELECT * FROM user_accounts WHERE id = :id")
    suspend fun getUserAccountById(id: String): UserAccountEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAccount(userAccount: UserAccountEntity)

    @Query("UPDATE user_accounts SET balanceCents = :newBalanceCents, lastUpdateAt = :timestamp WHERE id = :accountId")
    suspend fun updateBalance(accountId: String, newBalanceCents: Long, timestamp: Long = System.currentTimeMillis())
}
