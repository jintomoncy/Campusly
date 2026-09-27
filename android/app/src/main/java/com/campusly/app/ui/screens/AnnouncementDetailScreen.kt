package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
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
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.components.CategoryChip
import com.campusly.app.ui.components.PriorityBadge
import com.campusly.app.ui.components.VerifiedSourceBadge
import com.campusly.app.ui.theme.*

@Composable
fun AnnouncementDetailScreen(
    announcementId: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val announcements by AnnouncementRepository.announcements.collectAsState()
    val announcement = announcements.find { it.id == announcementId }

    if (announcement == null) {
        Surface(modifier = Modifier.fillMaxSize(), color = OffWhite) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Announcement not found.", fontSize = 16.sp, color = Slate500)
            }
        }
        return
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OffWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Nav
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = "Announcement Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                IconButton(onClick = { AnnouncementRepository.toggleBookmark(announcement.id) }) {
                    Icon(
                        imageVector = if (announcement.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (announcement.isBookmarked) IndigoPrimary else Slate400
                    )
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Tags
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryChip(category = announcement.category)
                    PriorityBadge(priority = announcement.priority)
                    VerifiedSourceBadge(sourceName = announcement.source.name)
                }

                // Title
                Text(
                    text = announcement.structuredContent.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900,
                    lineHeight = 28.sp
                )

                // Extracted Structured Card Box
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PureWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "CAMPUSLY EXTRACTED ACTIONS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = IndigoPrimary,
                            letterSpacing = 1.sp
                        )

                        Divider(color = Slate100)

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "📅 Date:", fontSize = 13.sp, color = Slate500)
                            Text(text = announcement.structuredContent.date ?: "Not specified", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate900)
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "⏰ Time:", fontSize = 13.sp, color = Slate500)
                            Text(text = announcement.structuredContent.time ?: "Not specified", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate900)
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "📍 Venue:", fontSize = 13.sp, color = Slate500)
                            Text(text = announcement.structuredContent.location ?: "Not specified", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate900)
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "⚡ Action:", fontSize = 13.sp, color = Slate500)
                            Text(text = announcement.structuredContent.actionRequired ?: "Not specified", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = IndigoPrimary)
                        }
                    }
                }

                // Description Paragraph
                Text(
                    text = "Description & Summary",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = announcement.structuredContent.summary,
                    fontSize = 14.sp,
                    color = Slate700,
                    lineHeight = 20.sp
                )

                // Raw Source Text Box
                Text(
                    text = "Original Raw Announcement",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate500
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Slate100)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "\"${announcement.rawText}\"",
                        fontSize = 12.sp,
                        color = Slate600,
                        lineHeight = 16.sp
                    )
                }
            }

            // Bottom Actions (Add to Calendar & Mark Read)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        AnnouncementRepository.markAsRead(announcement.id)
                        Toast.makeText(context, "Marked as Read!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = if (announcement.isRead) "Read" else "Mark Read", fontSize = 14.sp)
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Added '${announcement.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .weight(1.5f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Slate900, contentColor = Color.White)
                ) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Add to Calendar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
