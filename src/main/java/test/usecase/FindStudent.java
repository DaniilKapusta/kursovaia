package test.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.Student;
import test.repository.StudentRepository;

import java.util.List;
@Service
public final class FindStudent {
    private Logger logger = LoggerFactory.getLogger(FindStudent.class);
    @Autowired
    private StudentRepository studentRepository;


    public Student findByIdLike(final Long id) {
        return studentRepository.findByIdLike(id);
    }

    public List<Student> findByNameContainingIgnoreCase(final String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Student> findByStudentsGroupContainingIgnoreCase(final String studentsGroup) {
        return studentRepository.findByStudentsGroupContainingIgnoreCase(studentsGroup);
    }
    public List<Student> findByBranchContainingIgnoreCase(final String branch){
        return studentRepository.findByBranchContainingIgnoreCase(branch);
    }
    public Student findByNameAndStudentsGroupAndBranchContainingIgnoreCase( String name,  String studentGroup,  String branch) {
        logger.info("name2 = " + name);
        logger.info("gr2 = " + studentGroup);
        logger.info("br2 = " + branch);
        if (studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch) == null)
        {
            logger.info("CLOWN");

              return null;
          }
        logger.info("HELLO EBAT");
        return studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch);
    }

    
}
