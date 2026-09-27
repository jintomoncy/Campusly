package com.campusly.app.data.models

enum class PriorityLevel(val label: String) {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low")
}

enum class AnnouncementCategory(val label: String) {
    EXAM("Exam"),
    ASSIGNMENT("Assignment"),
    EVENT("Event"),
    WORKSHOP("Workshop"),
    HACKATHON("Hackathon"),
    INTERNSHIP("Internship"),
    SCHOLARSHIP("Scholarship"),
    CLUB("Club"),
    PLACEMENT("Placement"),
    DEADLINE("Deadline"),
    EMERGENCY("Emergency"),
    GENERAL("General")
}

data class TargetAudience(
    val college: String = "Campusly Institute of Technology",
    val departments: List<String> = listOf("Information Technology"),
    val semesters: List<String> = listOf("S1"),
    val communities: List<String> = emptyList()
)

data class VerifiedSource(
    val id: String,
    val name: String,
    val role: String,
    val isVerified: Boolean = true,
    val isOfficial: Boolean = true
)

data class ConflictDetails(
    val conflictField: String,
    val sourceA: String,
    val sourceB: String,
    val status: String = "Verification required"
)

data class StructuredContent(
    val title: String,
    val category: AnnouncementCategory,
    val summary: String,
    val audienceSummary: String,
    val date: String? = null,
    val time: String? = null,
    val location: String? = null,
    val actionRequired: String? = null,
    val priority: PriorityLevel = PriorityLevel.MEDIUM
)

data class Announcement(
    val id: String,
    val title: String,
    val rawText: String,
    val structuredContent: StructuredContent,
    val audience: TargetAudience,
    val source: VerifiedSource,
    val category: AnnouncementCategory,
    val priority: PriorityLevel,
    val publishedAt: String,
    val isRead: Boolean = false,
    val isBookmarked: Boolean = false,
    val isConflictDetected: Boolean = false,
    val conflictDetails: ConflictDetails? = null
)
