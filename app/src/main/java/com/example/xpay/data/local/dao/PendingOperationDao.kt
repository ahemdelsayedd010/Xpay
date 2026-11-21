package com.example.xpay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xpay.data.local.entities.PendingOperationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingOperationDao {
    @Query("SELECT * FROM pending_operations WHERE status = 'PENDING' ORDER BY createdAt ASC")
    fun getPendingOperationsFlow(): Flow<List<PendingOperationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: PendingOperationEntity)

    @Query("UPDATE pending_operations SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: String)

    @Query("DELETE FROM pending_operations WHERE id = :id")
    suspend fun deleteOperation(id: String)
}
