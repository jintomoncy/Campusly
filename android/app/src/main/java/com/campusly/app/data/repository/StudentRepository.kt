package com.campusly.app.data.repository

import com.campusly.app.data.models.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object StudentRepository {
    private val _currentProfile = MutableStateFlow(StudentProfile())
    val currentProfile: StateFlow<StudentProfile> = _currentProfile.asStateFlow()

    fun updateProfile(
        college: String? = null,
        department: String? = null,
        semester: String? = null,
        communities: List<String>? = null
    ) {
        _currentProfile.update { current ->
            current.copy(
                college = college ?: current.college,
                department = department ?: current.department,
                semester = semester ?: current.semester,
                communities = communities ?: current.communities
            )
        }
    }
}
