package test.DTO;


import test.entity.Student;
import test.entity.TestResultEntity;

import java.util.List;

public class QueriesDTO {

    private String findMethod;
    private String studentName;
    private String studentGroup;
    private String studentBranch;
    private java.sql.Date studentDate;

    private Student findStudent;

    public Student getFindStudent() {
        return findStudent;
    }

    public void setFindStudent(Student findStudent) {
        this.findStudent = findStudent;
    }

    public List<TestResultEntity> getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(List<TestResultEntity> studentResult) {
        this.studentResult = studentResult;
    }

    private List<TestResultEntity> studentResult;

    public String getFindMethod() {
        return findMethod;
    }

    public void setFindMethod(String findMethod) {
        this.findMethod = findMethod;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getStudentBranch() {
        return studentBranch;
    }

    public void setStudentBranch(String studentBranch) {
        this.studentBranch = studentBranch;
    }

    public java.sql.Date getStudentDate() {
        return studentDate;
    }

    public void setStudentDate(java.sql.Date studentDate) {
        this.studentDate = studentDate;
    }

}
