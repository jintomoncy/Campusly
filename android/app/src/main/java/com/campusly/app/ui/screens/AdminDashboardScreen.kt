package com.campusly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.components.CategoryChip
import com.campusly.app.ui.components.PriorityBadge
import com.campusly.app.ui.theme.*

@Composable
fun AdminDashboardScreen(
    onCreateClick: () -> Unit,
    onSwitchToStudentView: () -> Unit
) {
    val announcements by AnnouncementRepository.announcements.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Admin Portal",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Slate900)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(text = "HOD Mode", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
                Text(
                    text = "Information Delivery Layer",
                    fontSize = 12.sp,
                    color = Slate500
                )
            }

            OutlinedButton(
                onClick = onSwitchToStudentView,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Student View", fontSize = 12.sp)
            }
        }

        // Metrics Overview Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricCard("Published", announcements.size.toString(), IndigoPrimary, Modifier.weight(1f))
            MetricCard("Scheduled", "2", Slate700, Modifier.weight(1f))
            MetricCard("Drafts", "1", Slate500, Modifier.weight(1f))
        }

        // Primary Create Announcement CTA Button
        Button(
            onClick = onCreateClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Slate900, contentColor = Color.White)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "+ Create Announcement with AI", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        // Recent Announcements List
        Text(
            text = "RECENTLY PUBLISHED ANNOUNCEMENTS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Slate500,
            letterSpacing = 1.sp
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(announcements, key = { it.id }) { ann ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PureWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CategoryChip(category = ann.category)
                            PriorityBadge(priority = ann.priority)
                        }

                        Text(
                            text = ann.structuredContent.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )

                        Text(
                            text = "Audience: ${ann.structuredContent.audienceSummary} · Published ${ann.publishedAt}",
                            fontSize = 12.sp,
                            color = Slate500
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricCard(title: String, count: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = count, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = color)
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = Slate500)
        }
    }
}
