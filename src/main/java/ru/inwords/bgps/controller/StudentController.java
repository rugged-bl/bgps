package ru.inwords.bgps.controller;

import org.springframework.web.bind.annotation.*;
import ru.inwords.bgps.dao.StudentJdbc;
import ru.inwords.bgps.model.Student;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Student get(@PathVariable int id) {
        return studentJdbc.get(id);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public int put(@RequestBody Student student) {
        return studentJdbc.update(student);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Student> getAll() {
        return studentJdbc.getAll();
    }

    @PostMapping(value = "/create", produces = "application/json")
    public int postCreate(@RequestBody Student student) {
        return studentJdbc.create(student);
    }

    @GetMapping(value = "/studygroupid/{id}", produces = "application/json")
    public List<Student> getStudyGroupId(@PathVariable int id) {
        return studentJdbc.getStudyGroupId(id);
    }

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public int delete(@PathVariable int id) {
        return studentJdbc.delete(id);
    }
}
