package com.example.xpay.data.repository

import com.example.xpay.data.local.dao.UserAccountDao
import com.example.xpay.data.local.entities.UserAccountEntity

class AccountRepository(private val accountDao: UserAccountDao) {
    val account = accountDao.getUserAccountFlow()

    suspend fun getAccountById(id: String) = accountDao.getUserAccountById(id)
    suspend fun insertAccount(account: UserAccountEntity) = accountDao.insertUserAccount(account)
    suspend fun updateBalance(accountId: String, newBalanceCents: Long, timestamp: Long = System.currentTimeMillis()) =
        accountDao.updateBalance(accountId, newBalanceCents, timestamp)

}