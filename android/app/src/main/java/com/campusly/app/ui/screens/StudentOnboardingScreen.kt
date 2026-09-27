package com.campusly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.repository.StudentRepository
import com.campusly.app.ui.theme.*

@Composable
fun StudentOnboardingScreen(
    onComplete: () -> Unit
) {
    var step by remember { mutableStateOf(1) }

    var selectedCollege by remember { mutableStateOf("Campusly Institute of Technology") }
    var selectedDepartment by remember { mutableStateOf("Information Technology") }
    var selectedSemester by remember { mutableStateOf("S1") }
    val selectedCommunities = remember { mutableStateListOf("IT Department", "IEDC", "Coding Club") }

    val departments = listOf(
        "Information Technology",
        "Computer Science & Engg",
        "Electronics & Communication",
        "Electrical & Electronics",
        "Mechanical Engineering"
    )

    val semesters = listOf("S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8")

    val availableCommunities = listOf("IT Department", "IEDC Cell", "IEEE Student Branch", "Coding Club", "Placement Cell")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OffWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar & Progress
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (step > 1) {
                        IconButton(onClick = { step-- }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    } else {
                        Spacer(modifier = Modifier.size(48.dp))
                    }
                    Text(
                        text = "Step $step of 3",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate500
                    )
                    Spacer(modifier = Modifier.size(48.dp))
                }

                LinearProgressIndicator(
                    progress = { step / 3f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(CircleShape),
                    color = IndigoPrimary,
                    trackColor = Slate200
                )
            }

            // Step Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (step) {
                    1 -> {
                        Text(
                            text = "Select your Department",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = "Campusly uses your department to deliver relevant announcements.",
                            fontSize = 13.sp,
                            color = Slate600
                        )

                        departments.forEach { dept ->
                            val isSelected = selectedDepartment == dept
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) IndigoLight else PureWhite)
                                    .border(
                                        1.dp,
                                        if (isSelected) IndigoPrimary else Slate200,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { selectedDepartment = dept }
                                    .padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = dept,
                                        fontSize = 15.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) IndigoPrimary else Slate800
                                    )
                                    if (isSelected) {
                                        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = IndigoPrimary)
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        Text(
                            text = "Select your Semester",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = "Choose your current academic semester.",
                            fontSize = 13.sp,
                            color = Slate600
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            semesters.chunked(4).forEach { row ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    row.forEach { sem ->
                                        val isSelected = selectedSemester == sem
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(56.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(if (isSelected) Slate900 else PureWhite)
                                                .border(1.dp, if (isSelected) Slate900 else Slate200, RoundedCornerShape(12.dp))
                                                .clickable { selectedSemester = sem },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = sem,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSelected) Color.White else Slate800
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    3 -> {
                        Text(
                            text = "Select Communities & Clubs",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = "Join clubs to receive event and opportunity updates.",
                            fontSize = 13.sp,
                            color = Slate600
                        )

                        availableCommunities.forEach { comm ->
                            val isSelected = selectedCommunities.contains(comm)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) IndigoLight else PureWhite)
                                    .border(1.dp, if (isSelected) IndigoPrimary else Slate200, RoundedCornerShape(12.dp))
                                    .clickable {
                                        if (isSelected) selectedCommunities.remove(comm) else selectedCommunities.add(comm)
                                    }
                                    .padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = comm,
                                        fontSize = 14.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) IndigoPrimary else Slate800
                                    )
                                    Checkbox(
                                        checked = isSelected,
                                        onCheckedChange = {
                                            if (it) selectedCommunities.add(comm) else selectedCommunities.remove(comm)
                                        },
                                        colors = CheckboxDefaults.colors(checkedColor = IndigoPrimary)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Continue Button
            Button(
                onClick = {
                    if (step < 3) {
                        step++
                    } else {
                        StudentRepository.updateProfile(
                            college = selectedCollege,
                            department = selectedDepartment,
                            semester = selectedSemester,
                            communities = selectedCommunities.toList()
                        )
                        onComplete()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Slate900, contentColor = Color.White)
            ) {
                Text(
                    text = if (step == 3) "Complete Onboarding" else "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
            }
        }
    }
}
