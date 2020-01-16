package test.service;

import test.Dto.StudentDto;

import java.util.List;

public interface FindStudentInterface {
     StudentDto findByIdLike(final Long id);
     List<StudentDto> findByNameContainingIgnoreCase(final String name);
     List<StudentDto> findByStudentsGroupContainingIgnoreCase(final String studentsGroup);
     List<StudentDto> findByBranchContainingIgnoreCase(final String branch);
     StudentDto findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch);

}
