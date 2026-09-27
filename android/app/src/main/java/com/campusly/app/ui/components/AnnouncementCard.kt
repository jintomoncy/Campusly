package com.campusly.app.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.Announcement
import com.campusly.app.ui.theme.*

@Composable
fun AnnouncementCard(
    announcement: Announcement,
    onCardClick: () -> Unit = {},
    onBookmarkToggle: () -> Unit = {},
    onAddToCalendar: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Row: Category, Priority, Verified Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryChip(category = announcement.category)
                    VerifiedSourceBadge(sourceName = announcement.source.name)
                }
                PriorityBadge(priority = announcement.priority)
            }

            // Title & Summary
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = announcement.structuredContent.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate900,
                    lineHeight = 22.sp
                )
                Text(
                    text = announcement.structuredContent.summary,
                    fontSize = 13.sp,
                    color = Slate600,
                    lineHeight = 18.sp
                )
            }

            // Conflict Warning Alert Banner
            if (announcement.isConflictDetected && announcement.conflictDetails != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(RoseConflictBg)
                        .border(1.dp, RoseConflictBorder, RoundedCornerShape(10.dp))
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Conflict Warning",
                        tint = RoseConflictText,
                        modifier = Modifier.size(16.dp)
                    )
                    Column {
                        Text(
                            text = "⚠ Conflict Detected (${announcement.conflictDetails.conflictField})",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = RoseConflictText
                        )
                        Text(
                            text = "${announcement.conflictDetails.sourceA} vs ${announcement.conflictDetails.sourceB}",
                            fontSize = 11.sp,
                            color = RoseConflictText
                        )
                    }
                }
            }

            // Structured Details Grid (Date, Time, Location, Action)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Slate50)
                    .border(1.dp, Slate200, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "📅 ${announcement.structuredContent.date ?: "Not specified"}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate800
                    )
                    Text(
                        text = "⏰ ${announcement.structuredContent.time ?: "Not specified"}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Slate800
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "📍 ${announcement.structuredContent.location ?: "Not specified"}",
                        fontSize = 12.sp,
                        color = Slate600
                    )
                    Text(
                        text = "⚡ ${announcement.structuredContent.actionRequired ?: "Not specified"}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = IndigoPrimary
                    )
                }
            }

            // Footer Actions (Add to Calendar + Bookmark)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onAddToCalendar()
                        Toast.makeText(context, "Added '${announcement.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Slate900, contentColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Add to Calendar",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Add to Calendar", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }

                IconButton(onClick = onBookmarkToggle) {
                    Icon(
                        imageVector = if (announcement.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (announcement.isBookmarked) IndigoPrimary else Slate400
                    )
                }
            }
        }
    }
}
