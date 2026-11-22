package com.example.xpay.data.model

enum class Category(val label: String) { FOOD("Food"), SUBSCRIPTION("Subscription") /* ...yours */ }
enum class TransactionType { INCOME, EXPENSE }
enum class TransactionStatus { PENDING, COMPLETED, FAILED }