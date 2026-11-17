package com.example.xpay.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpay.R
import com.example.xpay.navigation.BottomMenu
import com.example.xpay.navigation.BottomMenuContent
import com.example.xpay.ui.components.Action
import com.example.xpay.ui.components.AmountText
import com.example.xpay.ui.components.Transaction
import com.example.xpay.ui.components.TransationItemTile
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

@Composable
fun CardsScreen() {
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

            TopSection()

            Spacer(modifier = Modifier.height(12.dp))

            // Dark Physical Card Widget
            CardDetails()

            Spacer(modifier = Modifier.height(18.dp))

            // Quick Action Row (Freeze, Limits, Details, Top up)
            val sampleActions = listOf(
                Action("Freeze", R.drawable.ic_scan),
                Action("Limits", R.drawable.ic_speedometer),
                Action("Details", R.drawable.ic_info),
                Action("Top up", R.drawable.ic_wallet)
            )
            QuickActionSection(actions = sampleActions)

            Spacer(modifier = Modifier.height(24.dp))
            MonthlyLimitCard()

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
                )
            )
            TransationItemTile(transactions = sampleTransactions)

            Spacer(modifier = Modifier.height(100.dp)) // Extra space for floating bottom bar
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { /* back action */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_caret_left),
                contentDescription = "back arrow",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "My Cards",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.Black,
        )
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { /* add card action */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "add new card",
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun CardDetails(
    cardHolder: String = "Ahmed Elsayed",
    cardNumber: String = "•••• •••• •••• 5897",
    balanceCents: Long = 1524500L,
    cardExpires: String = "05/2029",
    cardType: Painter = painterResource(R.drawable.ic_mastercard)
) {
    Box(
        modifier = Modifier
            .height(210.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Physical Card",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                Icon(
                    painter = painterResource(R.drawable.ic_eye),
                    contentDescription = "Hide",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            Column {
                Text(
                    text = "Available Balance",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                AmountText(
                    modifier = Modifier,
                    amountCents = balanceCents,
                    fontSize = 28.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = cardNumber,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }

            // Bottom card details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "Card Holder",
                        color = Color.LightGray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = cardHolder,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Column {
                    Text(
                        text = "Expires",
                        color = Color.LightGray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = cardExpires,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Icon(
                    painter = cardType,
                    contentDescription = "card type",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(44.dp)
                )
            }
        }
    }
}

/**
 * Responsive Action Row across full screen width
 */
@Composable
fun QuickActionSection(
    actions: List<Action>,
    modifier: Modifier = Modifier,
    onActionClick: (Action) -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        actions.forEach { action ->
            CardActionItems(
                action = action,
                modifier = Modifier.weight(1f),
                onClick = { onActionClick(action) }
            )
        }
    }
}

@Composable
fun CardActionItems(
    action: Action,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(1f) // Equal square shape
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(action.iconId),
                contentDescription = action.title,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = action.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsScreenPreview() {
    XpayTheme {
        CardsScreen()
    }
}
