package com.example.xpay.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AmountText(
    amountCents: Long,
    modifier: Modifier = Modifier,
    isMasked: Boolean = false,
    fontSize: TextUnit = 32.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    decimalColor: Color? = null,
    decimalScale: Float = 0.6f,
    prefix: String = "",
    fontWeight: FontWeight = FontWeight.Bold
) {
    val actualDecimalScale = if (fontSize <= 18.sp) 1f else decimalScale
    val actualDecimalColor = decimalColor ?: if (fontSize <= 18.sp) color else color.copy(alpha = 0.5f)

    val text = remember(amountCents, isMasked, fontSize, actualDecimalColor, actualDecimalScale, prefix, color) {
        if (isMasked) {
            AnnotatedString("••••••••")
        } else {
            val abs = kotlin.math.abs(amountCents)
            val dollars = NumberFormat.getNumberInstance(Locale.US).format(abs / 100)
            val decimals = "%02d".format(abs % 100)
            buildAnnotatedString {
                append("$prefix\$$dollars")
                if (actualDecimalScale == 1f && actualDecimalColor == color) {
                    append(".$decimals")
                } else if (actualDecimalScale == 1f) {
                    withStyle(SpanStyle(color = actualDecimalColor)) {
                        append(".$decimals")
                    }
                } else {
                    withStyle(SpanStyle(fontSize = fontSize * actualDecimalScale, color = actualDecimalColor)) {
                        append(".$decimals")
                    }
                }
            }
        }
    }
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier
    )
}