package com.example.xpay.data.repository

import com.example.xpay.data.local.dao.CardDao
import com.example.xpay.data.local.entities.CardEntity

class CardRepository(private val cardDao: CardDao) {
    val allCards = cardDao.getAllCardsFlow()

    suspend fun updateCardFreezeStatus(cardId: String, isFozen: Boolean) = cardDao.updateCardFreezeStatus(cardId = cardId, isFrozen = isFozen)
    suspend fun updateMonthlyLimit(cardId: String, limitCents: Long) = cardDao.updateMonthlyLimit(cardId = cardId, limitCents = limitCents)
    suspend fun insertCard(card: CardEntity) = cardDao.insertCard(card)


}