package com.campusly.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.*
import com.campusly.app.data.repository.AnnouncementRepository
import com.campusly.app.ui.theme.*
import java.util.UUID

@Composable
fun AdminReviewPublishScreen(
    rawInputText: String,
    onBack: () -> Unit,
    onPublishSuccess: () -> Unit
) {
    val context = LocalContext.current

    // Simulate AI extraction based on text input
    val isPython = rawInputText.contains("Python", ignoreCase = true)
    val isEce = rawInputText.contains("ECE", ignoreCase = true)

    var title by remember { mutableStateOf(if (isPython) "Python Lab Examination" else if (isEce) "VLSI Chip Design Workshop" else "Extracted Announcement") }
    var department by remember { mutableStateOf(if (isEce) "Electronics & Communication" else "Information Technology") }
    var semester by remember { mutableStateOf(if (isEce) "S3" else "S1") }
    var category by remember { mutableStateOf(if (isEce) AnnouncementCategory.WORKSHOP else AnnouncementCategory.EXAM) }
    var priority by remember { mutableStateOf(if (isEce) PriorityLevel.MEDIUM else PriorityLevel.HIGH) }
    var date by remember { mutableStateOf(if (isPython) "October 4, 2026" else if (isEce) "October 10, 2026" else "October 15, 2026") }
    var time by remember { mutableStateOf(if (isPython) "10:00 AM" else "09:30 AM") }
    var location by remember { mutableStateOf(if (isPython) "Lab 2 (IT Block)" else "Seminar Hall B") }
    var actionRequired by remember { mutableStateOf(if (isPython) "Bring completed lab records & ID card" else "Register before Oct 6 deadline") }

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
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Column {
                    Text(
                        text = "Review Extracted Information",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "Review and edit before publishing to students.",
                        fontSize = 12.sp,
                        color = Slate600
                    )
                }
            }

            // Editable Fields Form
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "CAMPUSLY UNDERSTOOD THIS AS:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = IndigoPrimary,
                    letterSpacing = 1.sp
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = department,
                        onValueChange = { department = it },
                        label = { Text("Department") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    OutlinedTextField(
                        value = semester,
                        onValueChange = { semester = it },
                        label = { Text("Semester") },
                        modifier = Modifier.width(90.dp),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Date") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Time") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = actionRequired,
                    onValueChange = { actionRequired = it },
                    label = { Text("Required Action Item") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
            }

            // Publish CTA
            Button(
                onClick = {
                    val newAnn = Announcement(
                        id = "ann-${UUID.randomUUID().toString().take(6)}",
                        title = title,
                        rawText = rawInputText,
                        structuredContent = StructuredContent(
                            title = title,
                            category = category,
                            summary = "$actionRequired at $location.",
                            audienceSummary = "$semester $department",
                            date = date,
                            time = time,
                            location = location,
                            actionRequired = actionRequired,
                            priority = priority
                        ),
                        audience = TargetAudience(
                            departments = listOf(department),
                            semesters = listOf(semester)
                        ),
                        source = VerifiedSource(
                            id = "src-hod",
                            name = "$department Dept",
                            role = "Class Coordinator",
                            isVerified = true,
                            isOfficial = true
                        ),
                        category = category,
                        priority = priority,
                        publishedAt = "Just now"
                    )

                    AnnouncementRepository.publishAnnouncement(newAnn)
                    Toast.makeText(context, "Announcement Published Successfully!", Toast.LENGTH_LONG).show()
                    onPublishSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Slate900, contentColor = Color.White)
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Publish Announcement to $semester $department", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
