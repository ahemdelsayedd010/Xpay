package com.example.xpay.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

fun formatDollarAmount(amount: Long): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    return "$" + formatter.format(amount)
}

@Composable
fun MonthlyLimitCard(
    modifier: Modifier = Modifier,
    totalSpent: Long = 8545,
    monthlyLimit: Long = 15245
) {
    val progress = (totalSpent.toFloat() / monthlyLimit.toFloat()).coerceIn(0f, 1f)
    val percentRemaining = (100 - ((totalSpent.toFloat() / monthlyLimit.toFloat()) * 100)).toInt()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Spent this month",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    text = "${formatDollarAmount(totalSpent)} / ${formatDollarAmount(monthlyLimit)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = Color.Black,
                trackColor = Color(0xFFF0F1F5)
            )
            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${percentRemaining}% of your monthly limit",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "100%",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}