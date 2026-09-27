package com.campusly.app.navigation

sealed class Screen(val route: String) {
    object Landing : Screen("landing")
    object RoleSelection : Screen("role_selection")
    object StudentOnboarding : Screen("student_onboarding")
    object StudentMain : Screen("student_main")
    object AnnouncementDetail : Screen("announcement_detail/{id}") {
        fun createRoute(id: String) = "announcement_detail/$id"
    }
    object WhatYouMissed : Screen("what_you_missed")
    object NotificationCenter : Screen("notification_center")
    object AdminDashboard : Screen("admin_dashboard")
    object AdminCreateAnnouncement : Screen("admin_create")
    object AdminReviewPublish : Screen("admin_review/{rawText}") {
        fun createRoute(rawText: String) = "admin_review/${java.net.URLEncoder.encode(rawText, "UTF-8")}"
    }
}

enum class StudentTab(val route: String, val label: String) {
    HOME("home", "Home"),
    EXPLORE("explore", "Explore"),
    CALENDAR("calendar", "Calendar"),
    OPPORTUNITIES("opportunities", "Opportunities"),
    SAVED("saved", "Saved")
}
