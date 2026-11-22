package com.example.xpay.data.model

enum class Category(val label: String) {
    FOOD("Food"),
    SUBSCRIPTIONS("Subscriptions"),
    TRANSFERS("Transfers"),
    INCOME("Income"),
    OTHER("Other")
}
enum class TransactionType {
    INCOME,
    EXPENSE
}
enum class TransactionStatus {
    PENDING,
    COMPLETED,
    FAILED
}