package com.example.xpay.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xpay.R
import com.example.xpay.ui.components.Action
import com.example.xpay.ui.components.AmountText
import com.example.xpay.ui.components.AppCard
import com.example.xpay.ui.components.QuickAction
import com.example.xpay.ui.components.TopBar
import com.example.xpay.ui.components.Transaction
import com.example.xpay.ui.components.TransationItemTile
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Top Header Bar
            TopBar()

            Spacer(modifier = Modifier.height(12.dp))

            // Savings Account Balance Card
            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AmountText(amountCents = 1524500L)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Quick Actions Grid
            val sampleActions = listOf(
                Action("Send Money", R.drawable.ph_send_money, "Send money to recipient"),
                Action("Airtime/Data", R.drawable.ph_airtime, "Recharge your mobile"),
                Action("Pay Bills", R.drawable.ph_pay_bills, "Pay bills, electricity etc."),
                Action("Cards", R.drawable.ph_cards, "You have 3 active cards")
            )
            QuickAction(actions = sampleActions, modifier = Modifier, onActionClick = {})

            Spacer(modifier = Modifier.height(12.dp))

            // Transaction History List
            val sampleTransactions = listOf(
                Transaction(
                    id = "1",
                    iconId = R.drawable.ph_send_money,
                    category = "Received",
                    title = "From Luke Kamaru",
                    amountCents = 50000L,
                    date = "Today, 1:00 PM",
                    isIncoming = true
                ),
                Transaction(
                    id = "2",
                    iconId = R.drawable.ph_pay_bills,
                    category = "Debit",
                    title = "To John Chris",
                    amountCents = 5000L,
                    date = "Today, 1:00 PM",
                    isIncoming = false
                ),
                Transaction(
                    id = "3",
                    iconId = R.drawable.ph_cards,
                    category = "Income",
                    title = "X Payout",
                    amountCents = 147500L,
                    date = "Today, 1:00 PM",
                    isIncoming = true
                ),
                Transaction(
                    id = "3",
                    iconId = R.drawable.ph_cards,
                    category = "Income",
                    title = "X Payout",
                    amountCents = 147500L,
                    date = "Today, 1:00 PM",
                    isIncoming = true
                ),
                Transaction(
                    id = "3",
                    iconId = R.drawable.ph_cards,
                    category = "Income",
                    title = "X Payout",
                    amountCents = 147500L,
                    date = "Today, 1:00 PM",
                    isIncoming = true
                )
            )
            TransationItemTile(transactions = sampleTransactions)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    XpayTheme {
        HomeScreen()
    }
}
