package test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.StudentDTO;
import test.entity.Student;
import test.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindStudentService implements FindStudentInterface {
    @Autowired
    private StudentRepository studentRepository;


    public StudentDTO findByIdLike(final Long id) {
        Student student = studentRepository.findByIdLike(id);
        return mapStudentToDto(student);
    }

    public List<StudentDTO> findByNameContainingIgnoreCase(final String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> studentDTOS.add(mapStudentToDto(st)));
        return studentDTOS;

    }
    public List<StudentDTO> findByStudentsGroupContainingIgnoreCase(final String studentsGroup) {
        List<Student> students = studentRepository.findByStudentsGroupContainingIgnoreCase(studentsGroup);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> studentDTOS.add(mapStudentToDto(st)));
        return studentDTOS;
    }
    public List<StudentDTO> findByBranchContainingIgnoreCase(final String branch){
        List<Student> students = studentRepository.findByBranchContainingIgnoreCase(branch);
        if (students == null)
            return null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        students.forEach(st -> studentDTOS.add(mapStudentToDto(st)));
        return studentDTOS;
    }
    public StudentDTO findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch) {
        if (studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch) == null)
        {
              return null;
          }
        Student student = studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch);
        return mapStudentToDto(student);
    }

    private StudentDTO mapStudentToDto(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentsGroup(student.getStudentsGroup());
        studentDTO.setBranch(student.getBranch());
        studentDTO.setName(student.getName());
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    
}
