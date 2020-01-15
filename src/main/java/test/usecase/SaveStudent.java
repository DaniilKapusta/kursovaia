package test.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.Student;
import test.repository.StudentRepository;
@Service
public final class SaveStudent {
    private Logger logger = LoggerFactory.getLogger(SaveStudent.class);
    @Autowired
    private StudentRepository studentRepository;
    public Student saveStudent(Student student) {
        logger.info("name1 = "+student.getName());
       return studentRepository.save(student);
    }
}
