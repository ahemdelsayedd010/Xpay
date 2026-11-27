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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpay.R
import com.example.xpay.ui.components.Action
import com.example.xpay.ui.components.AmountText
import com.example.xpay.ui.components.Transaction
import com.example.xpay.ui.components.TransationItemTile
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

private val sampleCardActions = listOf(
    Action("Freeze", R.drawable.ic_scan),
    Action("Limits", R.drawable.ic_speedometer),
    Action("Details", R.drawable.ic_info),
    Action("Top up", R.drawable.ic_wallet)
)

private val sampleCardTransactions = listOf(
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

private val sampleCards = listOf(
    Card(
        brandId = R.drawable.ic_mastercard3_foreground,
        holderName = "Arthur Taylor",
        cardNumber = "0000 0000 0000 5897",
        availableBalance = 1524500L,
        expiryMonth = 5,
        expiryYear = 2029,
        cardType = "Physical Card",
        cardBg = Color(0xFF0F0F11)
    ),
    Card(
        brandId = R.drawable.ic_visa2_foreground,
        holderName = "Arthur Taylor",
        cardNumber = "•••• •••• •••• 3412",
        availableBalance = 840000L,
        expiryMonth = 11,
        expiryYear = 2028,
        cardType = "Virtual Card",
        cardBg = Color(0xFF71717A)
    ),
    Card(
        brandId = R.drawable.ic_paypal_foreground,
        holderName = "Arthur Taylor",
        cardNumber = "•••• •••• •••• 9012",
        availableBalance = 320000L,
        expiryMonth = 8,
        expiryYear = 2030,
        cardType = "Virtual Card",
        cardBg = Color(0xFFEA580C)
    )
)

@Composable
fun CardsScreen() {
    var showAddCardSheet by remember { mutableStateOf(false) }
    var cardsList by remember { mutableStateOf(sampleCards) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            CardTopSection(onAddCardClick = { showAddCardSheet = true })
            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Stacked Cards Deck
                StackCards(cards = cardsList)

                Spacer(modifier = Modifier.height(18.dp))

                // Quick Action Row
                QuickActionSection(actions = sampleCardActions)

                Spacer(modifier = Modifier.height(24.dp))
                MonthlyLimitCard(totalSpent = 10500)

                Spacer(modifier = Modifier.height(12.dp))

                // Expenditure History List
                TransationItemTile(
                    transactions = sampleCardTransactions.filter { !it.isIncoming },
                    title = "Expenditures"
                )

                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        if (showAddCardSheet) {
            AddCardSheet(
                onDismiss = { showAddCardSheet = false },
                onAddNewCard = { newCard ->
                    cardsList = listOf(newCard) + cardsList
                    showAddCardSheet = false
                }
            )
        }
    }
}

@Composable
fun CardTopSection(
    onAddCardClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
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
                .clickable { onAddCardClick() },
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
fun StackCards(
    cards: List<Card>,
    modifier: Modifier = Modifier,
    onCardSelected: (Card) -> Unit = {}
) {
    var activeIndex by remember { mutableIntStateOf(0) }

    if (cards.isEmpty()) return

    val displayCount = cards.size.coerceAtMost(3)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(234.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        // Draw back to front: depth (displayCount - 1) down to 0
        for (depth in (displayCount - 1) downTo 0) {
            val cardIndex = (activeIndex + depth) % cards.size
            val card = cards[cardIndex]

            val topOffset = ((displayCount - 1 - depth) * 14).dp
            val sideMargin = (depth * 10).dp

            CardDetails(
                card = card,
                modifier = Modifier
                    .padding(top = topOffset, start = sideMargin, end = sideMargin),
                onClick = {
                    activeIndex = cardIndex
                    onCardSelected(card)
                }
            )
        }
    }
}

@Composable
fun CardDetails(
    card: Card,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var isMasked by remember { mutableStateOf(true) }

    val last4 = remember(card.cardNumber) { card.cardNumber.takeLast(4) }
    val displayCardNumber = if (isMasked) "•••• •••• •••• $last4" else card.cardNumber

    Box(
        modifier = modifier
            .height(210.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(card.cardBg ?: Color.Black)
            .clickable { onClick() }
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
                    text = card.cardType,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                Icon(
                    painter = painterResource(if (isMasked) R.drawable.ic_eye_slash else R.drawable.ic_eye),
                    contentDescription = if (isMasked) "Show Card Number" else "Hide Card Number",
                    tint = Color.White,
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .clickable { isMasked = !isMasked }
                )
            }

            Column {
                Text(
                    text = "Available Balance",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(2.dp))
                AmountText(
                    modifier = Modifier,
                    amountCents = card.availableBalance,
                    fontSize = 28.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = displayCardNumber,
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
                        text = card.holderName,
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
                        text = "${"%02d".format(card.expiryMonth)}/${card.expiryYear}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Icon(
                    painter = painterResource(card.brandId),
                    contentDescription = "card brand",
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
            .aspectRatio(1f)
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
