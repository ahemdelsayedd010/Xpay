package com.example.xpay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xpay.data.local.entities.TransactionEntity
import com.example.xpay.data.local.query.CategoryTotal
import com.example.xpay.data.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactionsFlow(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE category = :category ORDER BY timestamp DESC")
    fun getTransactionsByCategoryFlow(category: Category): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransactions(transactions: List<TransactionEntity>)

    @Query("""
        SELECT category, COUNT(*) AS transactionCount, SUM(amountCents) AS totalCents
        FROM transactions
        WHERE type = 'EXPENSE' AND timestamp BETWEEN :from AND :to
        GROUP BY category
        ORDER BY totalCents DESC
    """)
    fun categoryTotals(from: Long, to: Long): Flow<List<CategoryTotal>>

    @Query("""
        SELECT COALESCE(SUM(amountCents), 0) FROM transactions
        WHERE type = 'EXPENSE' AND timestamp BETWEEN :from AND :to
    """)
    fun spentBetween(from: Long, to: Long): Flow<Long>

    @Query("SELECT * FROM transactions ORDER BY timestamp DESC LIMIT :limit")
    fun recent(limit: Int): Flow<List<TransactionEntity>>
}