package com.campusly.app

import androidx.compose.runtime.*
import com.campusly.app.ui.screens.*
import com.campusly.app.ui.theme.CampuslyTheme

sealed class AppState {
    object Landing : AppState()
    object RoleSelection : AppState()
    object StudentOnboarding : AppState()
    object StudentMain : AppState()
    data class AnnouncementDetail(val id: String) : AppState()
    object WhatYouMissed : AppState()
    object NotificationCenter : AppState()
    object AdminDashboard : AppState()
    object AdminCreateAnnouncement : AppState()
    data class AdminReviewPublish(val rawText: String) : AppState()
}

@Composable
fun CampuslyApp() {
    var currentScreen by remember { mutableStateOf<AppState>(AppState.Landing) }

    CampuslyTheme {
        when (val screen = currentScreen) {
            is AppState.Landing -> LandingScreen(
                onGetStarted = { currentScreen = AppState.StudentOnboarding },
                onSignIn = { currentScreen = AppState.RoleSelection }
            )

            is AppState.RoleSelection -> RoleSelectionScreen(
                onSelectStudent = { currentScreen = AppState.StudentOnboarding },
                onSelectAdmin = { currentScreen = AppState.AdminDashboard }
            )

            is AppState.StudentOnboarding -> StudentOnboardingScreen(
                onComplete = { currentScreen = AppState.StudentMain }
            )

            is AppState.StudentMain -> StudentMainScreen(
                onAnnouncementClick = { id -> currentScreen = AppState.AnnouncementDetail(id) },
                onWhatYouMissedClick = { currentScreen = AppState.WhatYouMissed },
                onSearchClick = { },
                onNotificationClick = { currentScreen = AppState.NotificationCenter },
                onProfileClick = { currentScreen = AppState.RoleSelection }
            )

            is AppState.AnnouncementDetail -> AnnouncementDetailScreen(
                announcementId = screen.id,
                onBack = { currentScreen = AppState.StudentMain }
            )

            is AppState.WhatYouMissed -> WhatYouMissedScreen(
                onBack = { currentScreen = AppState.StudentMain }
            )

            is AppState.NotificationCenter -> NotificationCenterScreen(
                onBack = { currentScreen = AppState.StudentMain },
                onNotificationClick = { id -> currentScreen = AppState.AnnouncementDetail(id) }
            )

            is AppState.AdminDashboard -> AdminDashboardScreen(
                onCreateClick = { currentScreen = AppState.AdminCreateAnnouncement },
                onSwitchToStudentView = { currentScreen = AppState.StudentMain }
            )

            is AppState.AdminCreateAnnouncement -> AdminCreateAnnouncementScreen(
                onBack = { currentScreen = AppState.AdminDashboard },
                onStructureSubmit = { rawText -> currentScreen = AppState.AdminReviewPublish(rawText) }
            )

            is AppState.AdminReviewPublish -> AdminReviewPublishScreen(
                rawInputText = screen.rawText,
                onBack = { currentScreen = AppState.AdminCreateAnnouncement },
                onPublishSuccess = { currentScreen = AppState.StudentMain }
            )
        }
    }
}
