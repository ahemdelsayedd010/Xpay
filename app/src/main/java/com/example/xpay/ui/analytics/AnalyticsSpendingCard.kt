package com.example.xpay.ui.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpay.data.SpendBarItem
import com.example.xpay.ui.components.AmountText
import com.example.xpay.ui.theme.IncomeGreen
import com.example.xpay.ui.theme.IncomeGreenBg
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

private val sampleDailyBars = listOf(
    SpendBarItem("Mon", "Mon (This Week)", 32000L, 0.15f),
    SpendBarItem("Tue", "Tue (This Week)", 28000L, 0.15f),
    SpendBarItem("Wed", "Wed (This Week)", 41000L, 0.15f),
    SpendBarItem("Thu", "Thu (This Week)", 290000L, 0.85f),
    SpendBarItem("Fri", "Fri (This Week)", 116000L, 0.65f),
    SpendBarItem("Sat", "Sat (This Week)", 45000L, 0.15f),
    SpendBarItem("Sun", "Sun (This Week)", 15300L, 0.12f)
)

private val sampleWeeklyBars = listOf(
    SpendBarItem("Wk 1", "Week 1", 245000L, 0.85f),
    SpendBarItem("Wk 2", "Week 2", 158000L, 0.55f),
    SpendBarItem("Wk 3", "Week 3", 142000L, 0.50f),
    SpendBarItem("Wk 4", "Week 4", 289500L, 1.00f)
)

@Composable
fun AnalyticsSpendingCard(
    selectedTimeframe: String = "Week",
    modifier: Modifier = Modifier
) {
    val isWeekMode = (selectedTimeframe == "Week")
    val items = if (isWeekMode) sampleDailyBars else sampleWeeklyBars
    val totalAmountCents = if (isWeekMode) 273700L else 854500L

    var selectedIndex by remember(selectedTimeframe) {
        mutableStateOf(if (isWeekMode) 6 else 0)
    }
    val currentItem = items.getOrNull(selectedIndex) ?: items.first()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = if (isWeekMode) "TOTAL SPENT • THIS WEEK" else "TOTAL SPENT • THIS MONTH",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AmountText(
                        amountCents = totalAmountCents,
                        fontSize = 32.sp
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(IncomeGreenBg)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "📉 -8.4% vs last", // edit later
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = IncomeGreen
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = currentItem.title,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                    AmountText(
                        amountCents = currentItem.amountCents,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 1. Vertical Capsule Bars Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = (index == selectedIndex)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { selectedIndex = index },
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .width(if (isWeekMode) 24.dp else 42.dp)
                                .height((90 * item.heightRatio).coerceAtLeast(14f).dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) Color.Black else Color(0xFFE5E7EB))
                        )
                    }
                }
            }
            // 2. Horizontal Bottom Baseline
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFE5E7EB))
            )

            // 3. Day / Week Labels Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = (index == selectedIndex)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedIndex = index },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyticsSpendingCardPreview() {
    XpayTheme {
        Box(
            modifier = Modifier
                .background(LightBg)
                .padding(16.dp)
        ) {
            AnalyticsSpendingCard(selectedTimeframe = "Week")
        }
    }
}
