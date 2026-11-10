package com.example.xpay.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.TextPrimary

@Composable
fun QuickAction(
    actions: List<Action>,
    modifier: Modifier = Modifier,
    onActionClick: (Action) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LightBg)
    ) {
        // Header Row: "Quick Actions" | "More"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Quick Actions",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "More",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = TextPrimary
            )
        }

        // 2x2 Grid using 2 Rows with weight(1f) for equal 50% width
        if (actions.size >= 4) {
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                // Row 1 (Send Money & Airtime/Data)
                Row(modifier = Modifier.fillMaxWidth()) {
                    ActionItems(
                        action = actions[0],
                        modifier = Modifier.weight(1f),
                        onClick = { onActionClick(actions[0]) }
                    )
                    ActionItems(
                        action = actions[1],
                        modifier = Modifier.weight(1f),
                        onClick = { onActionClick(actions[1]) }
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Row 2 (Pay Bills & Cards)
                Row(modifier = Modifier.fillMaxWidth()) {
                    ActionItems(
                        action = actions[2],
                        modifier = Modifier.weight(1f),
                        onClick = { onActionClick(actions[2]) }
                    )
                    ActionItems(
                        action = actions[3],
                        modifier = Modifier.weight(1f),
                        onClick = { onActionClick(actions[3]) }
                    )
                }
            }
        }
    }
}

@Composable
fun ActionItems(
    action: Action,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(3.dp)
            .aspectRatio(1.5f) // Aspect ratio: slightly wider than 1:1 square
            .clip(RoundedCornerShape(20.dp)) // 20dp soft rounded corners
            .background(Color.White) // Crisp white card background
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Icon at Top-Left
            Icon(
                painter = painterResource(action.iconId),
                contentDescription = action.title,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.TopStart)
            )

            // Title & Subtitle grouped at Bottom-Left
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = action.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = action.actionDetails,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickActionPreview() {
    val sampleActions = listOf(
        Action("Send Money", R.drawable.ph_send_money, "Send money to recipient"),
        Action("Airtime/Data", R.drawable.ph_airtime, "Recharge your mobile"),
        Action("Pay Bills", R.drawable.ph_pay_bills, "Pay bills, electricity etc."),
        Action("Cards", R.drawable.ph_cards, "You have 3 active cards")
    )
    QuickAction(actions = sampleActions)
}
