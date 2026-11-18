package com.example.xpay.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xpay.R
import com.example.xpay.ui.analytics.AnalyticsScreen
import com.example.xpay.ui.cards.CardsScreen
import com.example.xpay.ui.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "Home"
        ) {
            composable("Home") {
                HomeScreen()
            }
            composable("Cards") {
                CardsScreen()
            }
            composable("Analytics") {
                AnalyticsScreen()
            }
            composable("Settings") {
                // Implement later
            }
        }

        // Global Floating Bottom Navigation Bar (Optimized for instant switching)
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_house_simple, R.drawable.ic_house_simple_fill),
                BottomMenuContent("Cards", R.drawable.ic_cardholder, R.drawable.ic_cardholder_fill),
                BottomMenuContent("Analytics", R.drawable.ic_chart_bar, R.drawable.ic_chart_bar_fill),
                BottomMenuContent("Settings", R.drawable.ic_gear_six, R.drawable.ic_gear_six_fill),
            ),
            navController = navController,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            onItemClick = { item ->
                navController.navigate(item.route) {
                    // Pop up to the start destination to prevent backstack growth
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    // Prevent duplicate instances when tapping the same tab
                    launchSingleTop = true
                    // Restore screen state instantly without re-measuring
                    restoreState = true
                }
            }
        )
    }
}
