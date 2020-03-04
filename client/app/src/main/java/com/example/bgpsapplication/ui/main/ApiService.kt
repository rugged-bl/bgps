package com.example.bgpsapplication.ui.main

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("journal/study_group/{id}")
    fun getJournalRecordsByGroupId(@Path("id") id: Int): Single<List<JournalRecord>>

    @GET("study-group")
    fun getStudyGroups(): Single<List<StudyGroup>>
}