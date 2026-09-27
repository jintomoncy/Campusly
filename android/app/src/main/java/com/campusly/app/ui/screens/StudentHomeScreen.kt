package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.PriorityLevel
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.data.repository.StudentRepository
import com.campusly.app.ui.components.AnnouncementCard
import com.campusly.app.ui.components.CampuslyHeader
import com.campusly.app.ui.theme.*

@Composable
fun StudentHomeScreen(
    onAnnouncementClick: (String) -> Unit,
    onWhatYouMissedClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val context = LocalContext.current
    val announcements by AnnouncementRepository.announcements.collectAsState()
    val studentProfile by StudentRepository.currentProfile.collectAsState()

    // Personalization rule: match department or 'All'
    val personalizedFeed = announcements.filter { ann ->
        ann.audience.departments.contains(studentProfile.department) ||
        ann.audience.departments.contains("All")
    }

    val highPriorityItems = personalizedFeed.filter { it.priority == PriorityLevel.HIGH }
    val regularItems = personalizedFeed.filter { it.priority != PriorityLevel.HIGH }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
    ) {
        CampuslyHeader(
            onSearchClick = onSearchClick,
            onNotificationClick = onNotificationClick,
            onProfileClick = onProfileClick
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Greeting Header
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Good morning, ${studentProfile.name} 👋",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "${studentProfile.department} · ${studentProfile.semester}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = IndigoPrimary
                    )
                }
            }

            // WHAT YOU MISSED QUICK CATCHUP BANNER
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onWhatYouMissedClick() },
                    colors = CardDefaults.cardColors(containerColor = IndigoLight)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(IndigoPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Schedule,
                                    contentDescription = "What you missed",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "While You Were Away",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate900
                                )
                                Text(
                                    text = "3 critical updates need your review",
                                    fontSize = 12.sp,
                                    color = Slate600
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "View Catchup",
                            tint = IndigoPrimary
                        )
                    }
                }
            }

            // NEEDS YOUR ATTENTION SECTION (HIGH PRIORITY)
            if (highPriorityItems.isNotEmpty()) {
                item {
                    Text(
                        text = "NEEDS YOUR ATTENTION",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = HighPriorityText,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                items(highPriorityItems, key = { it.id }) { ann ->
                    AnnouncementCard(
                        announcement = ann,
                        onCardClick = { onAnnouncementClick(ann.id) },
                        onBookmarkToggle = { AnnouncementRepository.toggleBookmark(ann.id) },
                        onAddToCalendar = {
                            Toast.makeText(context, "Added '${ann.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

            // REGULAR COMING UP SECTION
            item {
                Text(
                    text = "COMING UP FOR YOU",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate500,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (regularItems.isEmpty() && highPriorityItems.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "You're all caught up! No active announcements for your profile.",
                            fontSize = 14.sp,
                            color = Slate500
                        )
                    }
                }
            } else {
                items(regularItems, key = { it.id }) { ann ->
                    AnnouncementCard(
                        announcement = ann,
                        onCardClick = { onAnnouncementClick(ann.id) },
                        onBookmarkToggle = { AnnouncementRepository.toggleBookmark(ann.id) },
                        onAddToCalendar = {
                            Toast.makeText(context, "Added '${ann.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
