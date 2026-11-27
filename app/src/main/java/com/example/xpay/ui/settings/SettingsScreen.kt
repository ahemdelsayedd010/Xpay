package com.example.xpay.ui.settings

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.xpay.ui.theme.CardSurfaceLight
import com.example.xpay.ui.theme.DebitRed
import com.example.xpay.ui.theme.LightBg
import com.example.xpay.ui.theme.XpayTheme

private val accountFeatures = listOf(
    Feature("Account Profile", R.drawable.ic_account_profile, "Ahmed Elsayed"),
    Feature("Notifications", R.drawable.ic_bell_simple, "Enabled"),
    Feature("Security & PIN", R.drawable.ic_password, "Protected")
)

private val preferenceFeatures = listOf(
    Feature("Currency", R.drawable.ic_currency_circle_dollar, "USD ($)"),
    Feature("Language", R.drawable.ic_language, "English"),
    Feature("Payment Methods", R.drawable.ic_cards, "3 Linked Cards")
)

private val supportFeatures = listOf(
    Feature("Help & Support", R.drawable.ic_support),
    Feature("App Version", R.drawable.ic_info, "v1.0.0")
)

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
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
            // Header Bar
            SettingsTopBar(onBackClick = onBackClick)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Profile Header Card
                UserProfileSection()

                Spacer(modifier = Modifier.height(20.dp))

                // Account Section
                SettingsSectionGroup(
                    sectionTitle = "Account & Security",
                    features = accountFeatures
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Preferences Section
                SettingsSectionGroup(
                    sectionTitle = "Preferences",
                    features = preferenceFeatures
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Support Section
                SettingsSectionGroup(
                    sectionTitle = "Support & About",
                    features = supportFeatures
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Log Out Button
                LogoutButton()

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun SettingsTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_caret_left),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Settings",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Box(modifier = Modifier.size(36.dp)) // Spacer to keep title centered
    }
}

@Composable
fun UserProfileSection(
    userName: String = "Ahmed Elsayed",
    userEmail: String = "ahmed.elsayed@xpay.com"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Circle
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF111827)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = userName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = userEmail,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFECFDF5))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Verified", // relate to user card
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10B981)
                )
            }
        }
    }
}

@Composable
fun SettingsSectionGroup(
    sectionTitle: String,
    features: List<Feature>,
    onFeatureClick: (Feature) -> Unit = {}
) {
    Column {
        Text(
            text = sectionTitle,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(CardSurfaceLight)
        ) {
            Column {
                features.forEachIndexed { index, feature ->
                    FeatureItems(
                        feature = feature,
                        onClickFeature = { onFeatureClick(feature) }
                    )
                    if (index < features.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color(0xFFF3F4F6)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureItems(
    feature: Feature,
    modifier: Modifier = Modifier,
    onClickFeature: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickFeature() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF3F4F6)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(feature.iconId),
                    contentDescription = feature.title,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = feature.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            feature.featureDetails?.let { details ->
                Text(
                    text = details,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Icon(
                painter = painterResource(R.drawable.caret_right),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color(0xFF9CA3AF)
            )
        }
    }
}

@Composable
fun LogoutButton(
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onLogoutClick() }
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Log Out",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = DebitRed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    XpayTheme {
        SettingsScreen()
    }
}
