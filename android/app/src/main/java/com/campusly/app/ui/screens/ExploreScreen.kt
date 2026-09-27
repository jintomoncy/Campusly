package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.AnnouncementCategory
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.components.AnnouncementCard
import com.campusly.app.ui.theme.*

@Composable
fun ExploreScreen(
    onAnnouncementClick: (String) -> Unit
) {
    val context = LocalContext.current
    val announcements by AnnouncementRepository.announcements.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf<AnnouncementCategory?>(null) }

    val filteredAnnouncements = announcements.filter { ann ->
        val matchesQuery = searchQuery.isBlank() ||
                ann.structuredContent.title.contains(searchQuery, ignoreCase = true) ||
                ann.structuredContent.summary.contains(searchQuery, ignoreCase = true)

        val matchesCategory = selectedCategoryFilter == null || ann.category == selectedCategoryFilter

        matchesQuery && matchesCategory
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Explore Campus Information",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Slate900
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search announcements, exams, workshops...", fontSize = 14.sp) },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Slate400) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = PureWhite,
                unfocusedContainerColor = PureWhite,
                focusedBorderColor = IndigoPrimary,
                unfocusedBorderColor = Slate200
            ),
            singleLine = true
        )

        // Filter Category Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedCategoryFilter == null,
                    onClick = { selectedCategoryFilter = null },
                    label = { Text("All") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Slate900,
                        selectedLabelColor = Color.White
                    )
                )
            }
            items(AnnouncementCategory.values()) { cat ->
                FilterChip(
                    selected = selectedCategoryFilter == cat,
                    onClick = {
                        selectedCategoryFilter = if (selectedCategoryFilter == cat) null else cat
                    },
                    label = { Text(cat.label) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = IndigoPrimary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // Filtered List
        if (filteredAnnouncements.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No announcements match your search or filter criteria.",
                    fontSize = 14.sp,
                    color = Slate500
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(filteredAnnouncements, key = { it.id }) { ann ->
                    AnnouncementCard(
                        announcement = ann,
                        onCardClick = { onAnnouncementClick(ann.id) },
                        onBookmarkToggle = { AnnouncementRepository.toggleBookmark(ann.id) },
                        onAddToCalendar = {
                            Toast.makeText(context, "Added to Calendar!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
