# Xpay - Modern Mobile Banking & Fintech App 💳📱

A pixel-perfect, modern Android mobile banking application built with **Jetpack Compose**, **Material 3**, **Room DB**, **DataStore**, and **Phosphor Icons**.

## 📂 Project Structure

```text
com.example.xpay
├── data
│   ├── local        # Room Database, Entities (Account, Card, Transaction) & DAOs
│   ├── preferences  # DataStore User Preferences Repository
│   └── repository   # Single Source of Truth Repository
├── navigation       # Navigation Graph & Custom Floating Bottom Navigation Bar
├── ui
│   ├── theme        # Color Palette, Typography & Material3 Theme
│   ├── components   # Reusable UI (AppCard, AmountText, QuickActionTile, TopBar, TransactionItemTile)
│   ├── home         # Home Screen & HomeViewModel
│   ├── cards        # Cards Screen & CardsViewModel
│   ├── analytics    # Analytics Tab & Canvas Charts
│   └── settings     # Settings & Dark Mode Preferences
└── MainActivity.kt  # Single Activity Entry Point
```
