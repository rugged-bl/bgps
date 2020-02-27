package ru.inwords.bgps.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.inwords.bgps.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id = ?", this::mapStudent, id);
    }

    public List<Student> getStudyGroupId(int study_group_id) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE study_group_id = ?", this::mapStudent, study_group_id);
    }

    public List<Student> getAll() {
        return jdbcTemplate.query("SELECT * FROM STUDENT", this::mapStudent);
    }

    public int create(Student student) {
        return jdbcTemplate.update("INSERT INTO STUDENT (id, surname, name, second_name,study_group_id) VALUES (?, ?, ?, ?, ?)",
                student.getId(),
                student.getSurname(),
                student.getName(),
                student.getSecond_name(),
                student.getStudy_group_id());
    }

    public int update(Student student) {
        return jdbcTemplate.update("UPDATE STUDENT SET surname = '?', name = '?', second_name = '?' ,study_group_id = ? WHERE id = ?",

                student.getSurname(),
                student.getName(),
                student.getSecond_name(),
                student.getStudy_group_id(),
                student.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM STUDENT WHERE id = ?", id);
    }

    private Student mapStudent(ResultSet resultSet, int i) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("surname"),
                resultSet.getString("name"),
                resultSet.getString("second_name"),
                resultSet.getInt("study_group_id")
        );
    }
}
