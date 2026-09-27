package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.AnnouncementCategory
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.components.AnnouncementCard
import com.campusly.app.ui.theme.*

@Composable
fun OpportunitiesScreen(
    onAnnouncementClick: (String) -> Unit
) {
    val context = LocalContext.current
    val announcements by AnnouncementRepository.announcements.collectAsState()

    val opportunities = announcements.filter {
        it.category in listOf(
            AnnouncementCategory.HACKATHON,
            AnnouncementCategory.WORKSHOP,
            AnnouncementCategory.INTERNSHIP,
            AnnouncementCategory.SCHOLARSHIP
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = "Opportunities for You",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            )
            Text(
                text = "Hackathons, Workshops, Internships & Scholarships",
                fontSize = 13.sp,
                color = Slate600
            )
        }

        if (opportunities.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No new active opportunities right now.", fontSize = 14.sp, color = Slate500)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(opportunities, key = { it.id }) { opp ->
                    AnnouncementCard(
                        announcement = opp,
                        onCardClick = { onAnnouncementClick(opp.id) },
                        onBookmarkToggle = { AnnouncementRepository.toggleBookmark(opp.id) },
                        onAddToCalendar = {
                            Toast.makeText(context, "Added '${opp.structuredContent.title}' to Calendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
