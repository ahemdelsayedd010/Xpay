package com.example.xpay.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpay.R
import com.example.xpay.ui.theme.LightBg


@Composable
fun SavingsAccountCard(
    accountNumber: String = "001551458947",
    balanceCents: Long = 1524500L,
    lastUpdatedText: String = "Last updated 1 min ago",
    modifier: Modifier = Modifier
) {
    var isMasked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        // Top subtitle savings account - ****
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SAVINGS ACCOUNT - $accountNumber",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Balance Amount with eye visibility
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AmountText(modifier = modifier,
                amountCents = balanceCents,
                isMasked = isMasked,
                fontSize = 32.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Box(modifier = Modifier
                .size(32.dp)
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable {
                    isMasked = !isMasked
                },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(if (!isMasked) R.drawable.ic_eye else R.drawable.ic_eye_slash),
                    contentDescription = "Toggle balance privacy",
                    Modifier.size(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ph_details),
                contentDescription = "details",
                tint = Color.Gray,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = lastUpdatedText,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}