package com.campusly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.ui.theme.*

@Composable
fun WhatYouMissedScreen(
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OffWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Column {
                    Text(
                        text = "While You Were Away",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "3 critical updates recovered without scrolling through chats.",
                        fontSize = 12.sp,
                        color = Slate600
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "⚡ Python Lab Time & Venue Updated", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Slate900)
                            Text(text = "Examination moved to Friday Oct 4 at 10 AM in Lab 2.", fontSize = 13.sp, color = Slate600)
                            Text(text = "Source: IT Department · 2 hours ago", fontSize = 11.sp, color = IndigoPrimary, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "🚀 IEDC Hackathon Registration Opened", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Slate900)
                            Text(text = "24-hour campus hackathon on Oct 18 with $5,000 cash prizes.", fontSize = 13.sp, color = Slate600)
                            Text(text = "Source: IEDC Cell · 4 hours ago", fontSize = 11.sp, color = IndigoPrimary, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "⚠ DBMS Assignment Conflict Flagged", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Slate900)
                            Text(text = "Conflicting evaluation date detected between HOD notice and WhatsApp group.", fontSize = 13.sp, color = Slate600)
                            Text(text = "Source: CS Department · 3 hours ago", fontSize = 11.sp, color = RoseConflictText, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}
