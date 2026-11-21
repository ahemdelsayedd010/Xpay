package com.example.xpay.ui.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.xpay.data.local.entity.CategoryExpense
import com.example.xpay.ui.cards.MonthlyLimitCard
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

private val sampleCategories = listOf(
    CategoryExpense(
        categoryName = "Food",
        transactionCount = 5,
        amountCents = 6291L, // $6,291.00
        percentage = 74,
        iconId = R.drawable.fork_knife,
        iconBgColor = Color(0xFFFFF7E6),  // Soft warm amber background
        iconTintColor = Color(0xFFD97706)  // Dark amber icon tint
    ),
    CategoryExpense(
        categoryName = "Transfers",
        transactionCount = 3,
        amountCents = 1830L, // $1,830.00
        percentage = 21,
        iconId = R.drawable.ic_arrow_up_right,
        iconBgColor = Color(0xFFE6F4EA),  // Soft green background
        iconTintColor = Color(0xFF137333)  // Dark green icon tint
    ),
    CategoryExpense(
        categoryName = "Subscriptions",
        transactionCount = 3,
        amountCents = 424L,  // $424.00
        percentage = 5,
        iconId = R.drawable.ic_arrows_left_right,
        iconBgColor = Color(0xFFFCE8E6),  // Soft pink background
        iconTintColor = Color(0xFFC5221F)  // Dark red icon tint
    )
)

@Composable
fun AnalyticsScreen() {
    var selectedTimeframe by remember { mutableStateOf("Week") }
    var showSimulateSheet by remember { mutableStateOf(false) }

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
            AnalyticsTopSection(
                selectedOption = selectedTimeframe,
                onOptionSelected = { newOption ->
                    selectedTimeframe = newOption
                }
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Spending Bar Chart Card (Dual Week/Month mode)
                AnalyticsSpendingCard(selectedTimeframe = selectedTimeframe)

                Spacer(modifier = Modifier.height(24.dp))
                MonthlyLimitCard()

                Spacer(modifier = Modifier.height(12.dp))
                TopCategoriesSection(
                    categoriesExpense = sampleCategories,
                    onSimulateClick = { showSimulateSheet = true }
                )

                Spacer(modifier = Modifier.height(100.dp)) // Extra space for floating bottom bar
            }
        }

        // Modal Bottom Sheet Dialog for Expense Simulation
        if (showSimulateSheet) {
            SimulateExpenseSheet(
                onDismiss = { showSimulateSheet = false },
                onAddExpense = { merchant, amountCents, category ->
                    // Expense simulation callback
                    showSimulateSheet = false
                }
            )
        }
    }
}

@Composable
fun AnalyticsTopSection(
    selectedOption: String = "Week",
    onOptionSelected: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
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
            text = "Analytics",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            TimeframeSegmentedToggle(
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }
    }
}

@Composable
fun TimeframeSegmentedToggle(
    selectedOption: String = "Week",
    onOptionSelected: (String) -> Unit = {}
) {
    val options = listOf("Week", "Month")

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            val isSelected = (option == selectedOption)
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (isSelected) Color.Black else Color.Transparent)
                    .clickable { onOptionSelected(option) }
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else Color(0xFF8E8E93)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyticsScreenPreview() {
    XpayTheme {
        AnalyticsScreen()
    }
}
