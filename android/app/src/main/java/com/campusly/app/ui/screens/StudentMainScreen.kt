package com.campusly.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.navigation.StudentTab
import com.campusly.app.ui.theme.*

@Composable
fun StudentMainScreen(
    onAnnouncementClick: (String) -> Unit,
    onWhatYouMissedClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(StudentTab.HOME) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = PureWhite,
                tonalElevation = 4.dp
            ) {
                StudentTab.values().forEach { tab ->
                    val isSelected = selectedTab == tab
                    val icon = when (tab) {
                        StudentTab.HOME -> Icons.Default.Home
                        StudentTab.EXPLORE -> Icons.Default.Explore
                        StudentTab.CALENDAR -> Icons.Default.CalendarToday
                        StudentTab.OPPORTUNITIES -> Icons.Default.Work
                        StudentTab.SAVED -> Icons.Default.Bookmark
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedTab = tab },
                        icon = { Icon(imageVector = icon, contentDescription = tab.label) },
                        label = { Text(text = tab.label, fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = IndigoPrimary,
                            selectedTextColor = IndigoPrimary,
                            indicatorColor = IndigoLight,
                            unselectedIconColor = Slate400,
                            unselectedTextColor = Slate500
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                StudentTab.HOME -> StudentHomeScreen(
                    onAnnouncementClick = onAnnouncementClick,
                    onWhatYouMissedClick = onWhatYouMissedClick,
                    onSearchClick = { selectedTab = StudentTab.EXPLORE },
                    onNotificationClick = onNotificationClick,
                    onProfileClick = onProfileClick
                )
                StudentTab.EXPLORE -> ExploreScreen(
                    onAnnouncementClick = onAnnouncementClick
                )
                StudentTab.CALENDAR -> CalendarScreen(
                    onAnnouncementClick = onAnnouncementClick
                )
                StudentTab.OPPORTUNITIES -> OpportunitiesScreen(
                    onAnnouncementClick = onAnnouncementClick
                )
                StudentTab.SAVED -> SavedScreen(
                    onAnnouncementClick = onAnnouncementClick
                )
            }
        }
    }
}
