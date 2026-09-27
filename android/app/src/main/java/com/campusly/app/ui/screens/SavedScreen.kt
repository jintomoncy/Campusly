package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.components.AnnouncementCard
import com.campusly.app.ui.theme.*

@Composable
fun SavedScreen(
    onAnnouncementClick: (String) -> Unit
) {
    val context = LocalContext.current
    val announcements by AnnouncementRepository.announcements.collectAsState()

    val savedItems = announcements.filter { it.isBookmarked }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = "Saved Announcements",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
            Text(
                text = "Your bookmarked notices and opportunities",
                fontSize = 13.sp,
                color = Slate600
            )
        }

        if (savedItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    tint = Slate400,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Nothing saved yet.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate800
                )
                Text(
                    text = "Save something useful and come back later.",
                    fontSize = 13.sp,
                    color = Slate500,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(savedItems, key = { it.id }) { item ->
                    AnnouncementCard(
                        announcement = item,
                        onCardClick = { onAnnouncementClick(item.id) },
                        onBookmarkToggle = { AnnouncementRepository.toggleBookmark(item.id) },
                        onAddToCalendar = {
                            Toast.makeText(context, "Added '${item.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
