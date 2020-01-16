package test.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.StudentDTO;
import test.entity.Student;
import test.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindStudent {
    private Logger logger = LoggerFactory.getLogger(FindStudent.class);
    @Autowired
    private StudentRepository studentRepository;


    public StudentDTO findByIdLike(final Long id) {
        Student student = studentRepository.findByIdLike(id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentsGroup(student.getStudentsGroup());
        studentDTO.setBranch(student.getBranch());
        studentDTO.setName(student.getName());
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    public List<StudentDTO> findByNameContainingIgnoreCase(final String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentsGroup(st.getStudentsGroup());
            studentDTO.setBranch(st.getBranch());
            studentDTO.setName(st.getName());
            studentDTO.setId(st.getId());
            studentDTOS.add(studentDTO);
        });
        return studentDTOS;

    }
    public List<StudentDTO> findByStudentsGroupContainingIgnoreCase(final String studentsGroup) {
        List<Student> students = studentRepository.findByStudentsGroupContainingIgnoreCase(studentsGroup);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentsGroup(st.getStudentsGroup());
            studentDTO.setBranch(st.getBranch());
            studentDTO.setName(st.getName());
            studentDTO.setId(st.getId());
            studentDTOS.add(studentDTO);
        });
        return studentDTOS;
    }
    public List<StudentDTO> findByBranchContainingIgnoreCase(final String branch){
        List<Student> students = studentRepository.findByBranchContainingIgnoreCase(branch);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentsGroup(st.getStudentsGroup());
            studentDTO.setBranch(st.getBranch());
            studentDTO.setName(st.getName());
            studentDTO.setId(st.getId());
            studentDTOS.add(studentDTO);
        });
        return studentDTOS;
    }
    public StudentDTO findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch) {
        logger.info("name2 = " + name);
        logger.info("gr2 = " + studentGroup);
        logger.info("br2 = " + branch);
        if (studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch) == null)
        {
            logger.info("CLOWN");

              return null;
          }
        logger.info("HELLO EBAT");
        Student student = studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch);
         StudentDTO student1 = new StudentDTO();
         student1.setId(student.getId());
         student1.setName(student.getName());
         student1.setBranch(student.getBranch());
         student1.setStudentsGroup(student.getStudentsGroup());
        return student1;
    }

    
}
