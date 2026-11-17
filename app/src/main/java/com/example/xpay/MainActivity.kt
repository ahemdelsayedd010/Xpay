package com.example.xpay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.xpay.navigation.Navigation
import com.example.xpay.ui.home.HomeScreen
import com.example.xpay.ui.theme.XpayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XpayTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}
