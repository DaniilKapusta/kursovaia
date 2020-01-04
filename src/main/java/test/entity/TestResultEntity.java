package test.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TestResult")
public class TestResultEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long studentId;
    private Integer correctAnswers;
    private Integer mark;
    private Integer questionCount;
    private java.sql.Date testingDate;

    public java.sql.Date getTestingDate() {
        return testingDate;
    }

    public void setTestingDate(java.sql.Date testingDate) {
        this.testingDate = testingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }


    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

}
