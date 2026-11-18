package com.example.xpay.navigation

import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomMenuContent) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(44.dp))
            .background(Color.White)
            .padding(vertical = 6.dp)
    ) {
        items.forEach { item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route
            BottomMenuItem(
                item = item,
                isSelected = isSelected
            ) {
                onItemClick(item)
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color(0xFFE8E8E8),
    activeTextColor: Color = Color.Black,
    inactiveTextColor: Color = Color(0xFF555555),
    onItemClick: () -> Unit
) {
    val currentIconId = if (isSelected) item.selectedIconId else item.iconId

    Box(
        modifier = Modifier
            .width(78.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(44.dp))
            .background(if (isSelected) activeHighlightColor else Color.Transparent)
            .clickable { onItemClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(currentIconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = item.title,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) activeTextColor else inactiveTextColor
            )
        }
    }
}
