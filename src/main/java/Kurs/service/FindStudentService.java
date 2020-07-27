package Kurs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Kurs.Dto.StudentDto;
import Kurs.entity.Student;
import Kurs.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindStudentService implements FindStudentInterface {
    @Autowired
    private StudentRepository studentRepository;


    public StudentDto findByIdLike(final Long id) {
        Student student = studentRepository.findByIdLike(id);
        return mapStudentToDto(student);
    }

    public List<StudentDto> findByNameContainingIgnoreCase(final String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        if (students == null)
            return null;
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(st -> studentDtos.add(mapStudentToDto(st)));
        return studentDtos;

    }
    public List<StudentDto> findByStudentsGroupContainingIgnoreCase(final String studentsGroup) {
        List<Student> students = studentRepository.findByStudentsGroupContainingIgnoreCase(studentsGroup);
        if (students == null)
            return null;
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(st -> studentDtos.add(mapStudentToDto(st)));
        return studentDtos;
    }
    public List<StudentDto> findByBranchContainingIgnoreCase(final String branch){
        List<Student> students = studentRepository.findByBranchContainingIgnoreCase(branch);
        if (students == null)
            return null;
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach(st -> studentDtos.add(mapStudentToDto(st)));
        return studentDtos;
    }
    public StudentDto findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch) {
        if (studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch) == null)
        {
              return null;
          }
        Student student = studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name, studentGroup, branch);
        return mapStudentToDto(student);
    }

    private StudentDto mapStudentToDto(Student student){
        StudentDto studentDTO = new StudentDto();
        studentDTO.setStudentsGroup(student.getStudentsGroup());
        studentDTO.setBranch(student.getBranch());
        studentDTO.setName(student.getName());
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    
}
