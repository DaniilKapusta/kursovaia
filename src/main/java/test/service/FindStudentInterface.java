package test.service;

import test.DTO.StudentDTO;
import test.entity.Student;

import java.util.List;

public interface FindStudentInterface {
     StudentDTO findByIdLike(final Long id);
     List<StudentDTO> findByNameContainingIgnoreCase(final String name);
     List<StudentDTO> findByStudentsGroupContainingIgnoreCase(final String studentsGroup);
     List<StudentDTO> findByBranchContainingIgnoreCase(final String branch);
     StudentDTO findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch);

}
