package ru.inwords.bgps.controller;

import org.springframework.web.bind.annotation.*;
import ru.inwords.bgps.dao.JournalRecordJdbc;
import ru.inwords.bgps.model.JournalRecord;
import ru.inwords.bgps.model.JournalRecordExpanded;

import java.util.List;

@RequestMapping("journal")
@RestController
public class JournalRecordController {
    private final JournalRecordJdbc journalRecordJdbc;

    public JournalRecordController(JournalRecordJdbc journalRecordJdbc) {
        this.journalRecordJdbc = journalRecordJdbc;
    }

    @GetMapping("/{id}")
    public JournalRecord getJournalRecord(@PathVariable int id) {
        return journalRecordJdbc.get(id);
    }

    @GetMapping("/student/{studentId}")
    public List<JournalRecord> getJournalRecordsByStudent(@PathVariable int studentId) {
        return journalRecordJdbc.getAllByStudent(studentId);
    }

    @GetMapping("/study_group/{studyGroupId}")
    public List<JournalRecord> getJournalRecordsByStudyGroup(@PathVariable int studyGroupId) {
        return journalRecordJdbc.getAllByStudyGroup(studyGroupId);
    }

    @PostMapping
    public long addJournalRecord(@RequestBody JournalRecord journalRecord) {
        return journalRecordJdbc.create(journalRecord);
    }

    @PutMapping("/{id}")
    public int updateJournalRecord(@PathVariable int id, @RequestBody JournalRecord journalRecord) {
        return journalRecordJdbc.update(id, journalRecord);
    }

    @DeleteMapping("/{id}")
    public int deleteJournalRecord(@PathVariable int id) {
        return journalRecordJdbc.delete(id);
    }
}