package com.campusly.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campusly.app.data.models.AnnouncementCategory
import com.campusly.app.data.models.PriorityLevel
import com.campusly.app.ui.theme.*

@Composable
fun PriorityBadge(priority: PriorityLevel, modifier: Modifier = Modifier) {
    val (bgColor, textColor, borderColor) = when (priority) {
        PriorityLevel.HIGH -> Triple(HighPriorityBg, HighPriorityText, HighPriorityBorder)
        PriorityLevel.MEDIUM -> Triple(MediumPriorityBg, MediumPriorityText, MediumPriorityBorder)
        PriorityLevel.LOW -> Triple(LowPriorityBg, LowPriorityText, LowPriorityBorder)
    }

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(bgColor)
            .border(1.dp, borderColor, CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (priority == PriorityLevel.HIGH) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "High Priority Warning",
                tint = HighPriorityText,
                modifier = Modifier.size(12.dp)
            )
        }
        Text(
            text = "${priority.label} Priority",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

@Composable
fun VerifiedSourceBadge(sourceName: String, isOfficial: Boolean = true, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(EmeraldVerifiedBg)
            .border(1.dp, EmeraldVerifiedBorder, CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Verified Source",
            tint = EmeraldVerifiedText,
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = sourceName,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = EmeraldVerifiedText
        )
    }
}

@Composable
fun CategoryChip(category: AnnouncementCategory, modifier: Modifier = Modifier) {
    val (bgColor, textColor) = when (category) {
        AnnouncementCategory.EXAM -> Color(0xFFFFE4E6) to Color(0xFFBE123C)
        AnnouncementCategory.WORKSHOP -> Color(0xFFF3E8FF) to Color(0xFF7E22CE)
        AnnouncementCategory.ASSIGNMENT -> Color(0xFFFEF3C7) to Color(0xFFB45309)
        AnnouncementCategory.HACKATHON -> Color(0xFFDBEAFE) to Color(0xFF1D4ED8)
        AnnouncementCategory.INTERNSHIP -> Color(0xFFCCFBF1) to Color(0xFF0F766E)
        AnnouncementCategory.SCHOLARSHIP -> Color(0xFFD1FAE5) to Color(0xFF047857)
        AnnouncementCategory.EVENT -> Color(0xFFE0E7FF) to Color(0xFF4338CA)
        else -> Slate100 to Slate700
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = category.label,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}
