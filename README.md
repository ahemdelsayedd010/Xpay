# Xpay - Modern Mobile Banking & Fintech App 💳📱

A pixel-perfect, modern Android mobile banking application built with **Jetpack Compose**, **Material 3**, **Room DB**, **DataStore**, and **Phosphor Icons**.

## 📂 Project Structure

```text
com.example.xpay
├── XpayApp.kt                  NEW: Application class, creates the database and repository
├── data
│   ├── model                   NEW: enums Category, TransactionType, TransactionStatus
│   ├── local
│   │   ├── entity              NEW: AccountEntity, CardEntity, TransactionEntity
│   │   ├── dao                 NEW: AccountDao, CardDao, TransactionDao
│   │   ├── query               NEW: CategoryTotal, DayTotal (query result classes)
│   │   ├── XpayDatabase.kt     NEW
│   │   └── DatabaseSeeder.kt   NEW: fake data on first launch
│   ├── mapper                  NEW: Entity → your existing UI models
│   ├── repository              NEW: XpayRepository (the single source of truth)
│   ├── preferences             DataStore (settings later)
│   └── SpendBarItem.kt         (existing)
├── ui
│   ├── home        HomeScreen.kt + HomeViewModel.kt          (ViewModel is new)
│   ├── cards       CardsScreen.kt + CardsViewModel.kt        (ViewModel is new)
│   ├── analytics   AnalyticsScreen.kt + AnalyticsViewModel.kt (ViewModel is new)
│   └── components, theme   (unchanged)
└── navigation                  (unchanged)
```
