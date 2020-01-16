package test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.StudentDTO;
import test.entity.Student;
import test.repository.StudentRepository;
@Service
public final class SaveStudentService implements SaveStudentInterface {
    private Logger logger = LoggerFactory.getLogger(SaveStudentService.class);
    @Autowired
    private StudentRepository studentRepository;
    public StudentDTO saveStudent(StudentDTO student) {
        logger.info("name1 = "+student.getName());
        Student student1 = new Student();
        student1.setName(student.getName());
        student1.setStudentsGroup(student.getStudentsGroup());
        student1.setBranch(student.getBranch());
        student.setId(studentRepository.save(student1).getId());
       return student;
    }
}
