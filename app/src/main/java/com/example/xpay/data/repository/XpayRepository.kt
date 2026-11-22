package com.example.xpay.data.repository


import com.example.xpay.data.local.dao.TransactionDao
import com.example.xpay.data.local.entities.TransactionEntity
import com.example.xpay.data.model.Category
import kotlinx.coroutines.flow.Flow


class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransactions = transactionDao.getAllTransactionsFlow()

    fun getTransactionsByCategory(category: Category) = transactionDao.getTransactionsByCategoryFlow(category)
    suspend fun insertTransaction(transaction: TransactionEntity) = transactionDao.insertTransaction(transaction)
    suspend fun insertAllTransactions(transactions: List<TransactionEntity>) = transactionDao.insertAllTransactions(transactions)
    fun categoryTotals(from: Long, to: Long) = transactionDao.categoryTotals(from = from, to = to)
    fun spentBetween(from: Long, to: Long) = transactionDao.spentBetween(from = from, to = to)
    fun recent(limit: Int) = transactionDao.recent(limit = limit)

}