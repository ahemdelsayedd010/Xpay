package com.example.xpay.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AmountText(
    modifier: Modifier = Modifier,
    amountCents: Long,
    isMasked: Boolean = false,
    fontSize: TextUnit = 32.sp,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    if (isMasked){
        Text("••••••••")
    }else {
        val formattedText = formatCents(amountCents)
        Text(
            text = formattedText,
            modifier = modifier,
            fontSize = fontSize,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }

}
fun formatCents(cents: Long): String {
    val dollars = cents / 100.0
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return "$" + formatter.format(dollars)
}