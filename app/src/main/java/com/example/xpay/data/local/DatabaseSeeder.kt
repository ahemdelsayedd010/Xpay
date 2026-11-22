package com.example.xpay.data.local

import androidx.room.withTransaction
import com.example.xpay.data.local.entities.CardEntity
import com.example.xpay.data.local.entities.TransactionEntity
import com.example.xpay.data.local.entities.UserAccountEntity
import com.example.xpay.data.model.Category
import com.example.xpay.data.model.TransactionStatus
import com.example.xpay.data.model.TransactionType
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object DatabaseSeeder {

    const val ACCOUNT_ID = "acc-main"
    const val CARD_ID = "card-main"

    // Runs once: if the account already exists, nothing happens.
    suspend fun seedIfEmpty(db: AppDatabase) {
        if (db.userAccountDao().getUserAccountById(ACCOUNT_ID) != null) return

        db.withTransaction {
            db.userAccountDao().insertUserAccount(
                UserAccountEntity(
                    id = ACCOUNT_ID,
                    name = "Ahmed Elsayed",
                    balanceCents = 1_524_500L,
                    maskedAccountNumber = "0012482983"
                )
            )
            db.cardDao().insertCard(
                CardEntity(
                    id = CARD_ID,
                    accountId = ACCOUNT_ID,
                    brand = "MASTERCARD",
                    type = "Physical",
                    last4 = "5897",
                    holderName = "Ahmed Elsayed",
                    expireMonth = 5,
                    expireYear = 2029,
                    isFrozen = false,
                    monthlyLimitCents = 1_524_500L
                )
            )
            db.transactionDao().insertAllTransactions(fakeTransactions())
        }
    }

    private class Template(
        val category: Category,
        val type: TransactionType,
        val merchants: List<String>,
        val minCents: Long,
        val maxCents: Long
    )

    private fun fakeTransactions(): List<TransactionEntity> {
        val random = Random(42)                       // same fake data every time
        val now = System.currentTimeMillis()
        val day = TimeUnit.DAYS.toMillis(1)

        val food = Template(Category.FOOD, TransactionType.EXPENSE,
            listOf("Uber Eats", "Starbucks", "McDonald's", "Talabat"), 1_500, 12_000)
        val subs = Template(Category.SUBSCRIPTIONS, TransactionType.EXPENSE,
            listOf("Apple Music", "Netflix", "Spotify", "Amazon Prime"), 1_000, 6_000)
        val transfers = Template(Category.TRANSFERS, TransactionType.EXPENSE,
            listOf("To John Chris", "To Sara Ali"), 10_000, 90_000)
        val income = Template(Category.INCOME, TransactionType.INCOME,
            listOf("Luke Kamaru", "X Payout", "Salary"), 20_000, 150_000)

        // 55% food, 20% subscriptions, 10% transfers, 15% income
        val pool = List(55) { food } + List(20) { subs } + List(10) { transfers } + List(15) { income }

        return List(60) {
            val t = pool[random.nextInt(pool.size)]
            TransactionEntity(
                accountId = ACCOUNT_ID,
                cardId = if (t.type == TransactionType.EXPENSE) CARD_ID else null,
                category = t.category,
                type = t.type,
                amountCents = random.nextLong(t.minCents, t.maxCents + 1),
                status = TransactionStatus.COMPLETED,
                merchant = t.merchants[random.nextInt(t.merchants.size)],
                timestamp = now - random.nextInt(0, 90) * day - random.nextLong(0, day)
            )
        }
    }
}