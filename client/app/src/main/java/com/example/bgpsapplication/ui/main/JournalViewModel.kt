package com.example.bgpsapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class JournalViewModel : ViewModel() {
    val journalRecords = MutableLiveData<List<JournalRecordCollapsed>>()
    val studyGroups = MutableLiveData<List<StudyGroup>>()

    private val compositeDisposable = CompositeDisposable()

    private val studyGroupNameSubject = BehaviorSubject.create<StudyGroup>()

    init {
        studyGroupNameSubject
            .doOnError { it.printStackTrace() }
            .onErrorResumeNext(Observable.empty())
            .flatMapSingle { getJournalRecords(it.id) }
            .subscribe({
                journalRecords.postValue(it)
            }, { it.printStackTrace() })
            .also { compositeDisposable.add(it) }

        apiService.getStudyGroups()
            .subscribeOn(Schedulers.io())
            .subscribe({
                studyGroups.postValue(it)
            }, { it.printStackTrace() })
            .also { compositeDisposable.add(it) }
    }

    private fun getJournalRecords(id: Int) = apiService.getJournalRecordsByGroupId(id)
        .map { list ->
            val map = LinkedHashMap<Int, JournalRecordCollapsed>()
            list.forEach {
                map.compute(it.studentId) { id: Int, record: JournalRecordCollapsed? ->
                    if (record == null) {
                        JournalRecordCollapsed(
                            it.id,
                            it.studentId,
                            it.studyPlanId,
                            it.inTime,
                            if (it.subjectShortName == "ПрИС" && !it.inTime) it.count else 0,
                            if (it.subjectShortName == "СИИ" && !it.inTime) it.count else 0,
                            it.markId,
                            it.studentFullName,
                            it.subjectName,
                            it.subjectShortName,
                            it.examType,
                            if (it.subjectShortName == "ПрИС") "${it.markValue} ${it.examType}" else "",
                            if (it.subjectShortName == "СИИ") "${it.markValue} ${it.examType}" else ""
                        )
                    } else {
                        when (it.subjectShortName) {
                            "ПрИС" -> record.copy(markPrIs = "${it.markValue} ${it.examType}")
                            "СИИ" -> record.copy(markSii = "${it.markValue} ${it.examType}")
                            else -> record
                        }.copy(
                            countPrIs = record.countPrIs + if (it.subjectShortName == "ПрИС" && !record.inTime) it.count else 0,
                            countSii = record.countSii + if (it.subjectShortName == "СИИ" && !record.inTime) it.count else 0
                        )
                    }
                }
            }
            map.values.toList()
        }
        .subscribeOn(Schedulers.io())

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun onGroupSelected(studyGroup: StudyGroup?) {
        studyGroup ?: return
        studyGroupNameSubject.onNext(studyGroup)
    }
}
