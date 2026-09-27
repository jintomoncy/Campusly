package com.campusly.app.data.repository

import com.campusly.app.data.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object AnnouncementRepository {

    private val initialAnnouncements = listOf(
        Announcement(
            id = "ann-1",
            title = "Python Lab Examination Schedule",
            rawText = "All S1 IT students are informed that the Python lab examination will be conducted on Friday October 4 at 10 AM in Lab 2. Students should bring their completed records.",
            structuredContent = StructuredContent(
                title = "Python Lab Examination",
                category = AnnouncementCategory.EXAM,
                summary = "Practical examination for S1 IT students in Lab 2.",
                audienceSummary = "S1 Information Technology",
                date = "October 4, 2026",
                time = "10:00 AM",
                location = "Lab 2 (IT Block)",
                actionRequired = "Bring completed lab records & ID card",
                priority = PriorityLevel.HIGH
            ),
            audience = TargetAudience(
                departments = listOf("Information Technology"),
                semesters = listOf("S1")
            ),
            source = VerifiedSource(
                id = "src-1",
                name = "IT Department",
                role = "Class Coordinator",
                isVerified = true,
                isOfficial = true
            ),
            category = AnnouncementCategory.EXAM,
            priority = PriorityLevel.HIGH,
            publishedAt = "2 hours ago",
            isRead = false,
            isBookmarked = true
        ),
        Announcement(
            id = "ann-2",
            title = "VLSI Chip Design Hands-on Workshop",
            rawText = "Department of ECE is organizing a 2-day workshop on Cadence Virtuoso for S3 ECE students on Oct 10 at Seminar Hall B. Registration deadline Oct 6.",
            structuredContent = StructuredContent(
                title = "VLSI Chip Design Workshop",
                category = AnnouncementCategory.WORKSHOP,
                summary = "Hands-on CAD tool training for Electronics & Communication students.",
                audienceSummary = "S3 Electronics & Communication",
                date = "October 10, 2026",
                time = "09:30 AM",
                location = "Seminar Hall B",
                actionRequired = "Register before Oct 6 deadline",
                priority = PriorityLevel.MEDIUM
            ),
            audience = TargetAudience(
                departments = listOf("Electronics & Communication"),
                semesters = listOf("S3")
            ),
            source = VerifiedSource(
                id = "src-2",
                name = "ECE Department",
                role = "IEEE Student Branch",
                isVerified = true,
                isOfficial = true
            ),
            category = AnnouncementCategory.WORKSHOP,
            priority = PriorityLevel.MEDIUM,
            publishedAt = "4 hours ago"
        ),
        Announcement(
            id = "ann-3",
            title = "IEDC Annual Campus Hackathon 2026",
            rawText = "Innovation and Entrepreneurship Development Centre (IEDC) invites registrations for the 24-hour hackathon on Oct 18. Open to all departments and semesters.",
            structuredContent = StructuredContent(
                title = "IEDC Campus Hackathon",
                category = AnnouncementCategory.HACKATHON,
                summary = "24-hour innovation hackathon with $5,000 cash prizes.",
                audienceSummary = "All Departments & Semesters",
                date = "October 18, 2026",
                time = "09:00 AM",
                location = "Main Auditorium",
                actionRequired = "Submit team registration (4 members max)",
                priority = PriorityLevel.MEDIUM
            ),
            audience = TargetAudience(
                departments = listOf("All"),
                semesters = listOf("All")
            ),
            source = VerifiedSource(
                id = "src-3",
                name = "IEDC Cell",
                role = "Innovation Nodal Officer",
                isVerified = true,
                isOfficial = true
            ),
            category = AnnouncementCategory.HACKATHON,
            priority = PriorityLevel.MEDIUM,
            publishedAt = "1 day ago"
        ),
        Announcement(
            id = "ann-4",
            title = "DBMS Project Submission & Date Conflict Notice",
            rawText = "Attention S3 Computer Science: DBMS project evaluation conflicting schedule reported.",
            structuredContent = StructuredContent(
                title = "DBMS Project Evaluation",
                category = AnnouncementCategory.ASSIGNMENT,
                summary = "Conflicting exam date published by two different department sources.",
                audienceSummary = "S3 Computer Science",
                date = "Verification Required",
                time = "11:00 AM",
                location = "CS Lab 1",
                actionRequired = "Await official HOD confirmation",
                priority = PriorityLevel.HIGH
            ),
            audience = TargetAudience(
                departments = listOf("Computer Science"),
                semesters = listOf("S3")
            ),
            source = VerifiedSource(
                id = "src-4",
                name = "CS Department",
                role = "Academic Coordinator",
                isVerified = true,
                isOfficial = true
            ),
            category = AnnouncementCategory.ASSIGNMENT,
            priority = PriorityLevel.HIGH,
            publishedAt = "3 hours ago",
            isConflictDetected = true,
            conflictDetails = ConflictDetails(
                conflictField = "Date",
                sourceA = "CS Dept Notice: Oct 4",
                sourceB = "Class WhatsApp: Oct 5",
                status = "Verification required"
            )
        )
    )

    private val _announcements = MutableStateFlow(initialAnnouncements)
    val announcements: StateFlow<List<Announcement>> = _announcements.asStateFlow()

    fun publishAnnouncement(newAnnouncement: Announcement) {
        _announcements.update { current ->
            listOf(newAnnouncement) + current
        }
    }

    fun toggleBookmark(id: String) {
        _announcements.update { list ->
            list.map { item ->
                if (item.id == id) item.copy(isBookmarked = !item.isBookmarked) else item
            }
        }
    }

    fun markAsRead(id: String) {
        _announcements.update { list ->
            list.map { item ->
                if (item.id == id) item.copy(isRead = true) else item
            }
        }
    }
}
