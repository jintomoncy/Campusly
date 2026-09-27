package com.campusly.app.data.models

data class StudentProfile(
    val id: String = "std-1",
    val name: String = "Jinto",
    val email: String = "jinto@campusly.edu",
    val college: String = "Campusly Institute of Technology",
    val department: String = "Information Technology",
    val semester: String = "S1",
    val year: String = "1st Year",
    val communities: List<String> = listOf("IT Department", "IEDC", "Coding Club")
)

data class AdminProfile(
    val id: String = "adm-1",
    val name: String = "Dr. Robert Vance",
    val email: String = "vance.hod@campusly.edu",
    val college: String = "Campusly Institute of Technology",
    val department: String = "Information Technology",
    val designation: String = "HOD - Information Technology",
    val sourceName: String = "IT Department"
)
