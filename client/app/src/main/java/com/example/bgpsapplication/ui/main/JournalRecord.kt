package com.example.bgpsapplication.ui.main

data class JournalRecord(
    val id: Int,
    val studentId: Int,
    val studyPlanId: Int,
    val inTime: Boolean,
    val count: Int,
    val markId: Int,
    val studentFullName: String,
    val subjectName: String,
    val subjectShortName: String,
    val examType: String,
    val markName: String,
    val markValue: String
)

data class JournalRecordCollapsed(
    val id: Int,
    val studentId: Int,
    val studyPlanId: Int,
    val inTime: Boolean,
    val countPrIs: Int,
    val countSii: Int,
    val markId: Int,
    val studentFullName: String,
    val subjectName: String,
    val subjectShortName: String,
    val examType: String,
    val markPrIs: String,
    val markSii: String
)