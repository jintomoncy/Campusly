package com.campusly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.ui.theme.*

@Composable
fun AdminCreateAnnouncementScreen(
    onBack: () -> Unit,
    onStructureSubmit: (String) -> Unit
) {
    var rawText by remember {
        mutableStateOf("All S1 IT students are informed that the Python lab examination will be conducted on Friday October 4 at 10 AM in Lab 2. Students should bring their completed records.")
    }
    var isProcessing by remember { mutableStateOf(false) }

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
                        text = "Create Announcement",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = "Write in natural language. AI will extract structured fields.",
                        fontSize = 12.sp,
                        color = Slate600
                    )
                }
            }

            // Input Text Box & Quick Inserts
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "RAW ANNOUNCEMENT TEXT",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate500,
                    letterSpacing = 1.sp
                )

                OutlinedTextField(
                    value = rawText,
                    onValueChange = { rawText = it },
                    placeholder = { Text("Write your announcement in your own words...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = PureWhite,
                        unfocusedContainerColor = PureWhite,
                        focusedBorderColor = IndigoPrimary,
                        unfocusedBorderColor = Slate200
                    )
                )

                Text(
                    text = "Quick Example Presets:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Slate600
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = {
                            rawText = "All S1 IT students are informed that the Python lab examination will be conducted on Friday October 4 at 10 AM in Lab 2. Students should bring their completed records."
                        },
                        label = { Text("Python Exam (S1 IT)", fontSize = 11.sp) }
                    )
                    AssistChip(
                        onClick = {
                            rawText = "Department of ECE is organizing a 2-day workshop on Cadence Virtuoso for S3 ECE students on Oct 10 at Seminar Hall B. Registration deadline Oct 6."
                        },
                        label = { Text("VLSI Workshop (S3 ECE)", fontSize = 11.sp) }
                    )
                }
            }

            // Submit Button
            Button(
                onClick = {
                    if (rawText.isNotBlank()) {
                        isProcessing = true
                        onStructureSubmit(rawText)
                    }
                },
                enabled = !isProcessing && rawText.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = IndigoPrimary, contentColor = Color.White)
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Campusly is understanding this announcement...", fontSize = 14.sp)
                } else {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Structure with Campusly", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
