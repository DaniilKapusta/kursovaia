package test.usecase;

import test.entity.Student;
import test.repository.StudentRepository;

import java.util.List;

public final class FindStudent {

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
    public Student findByNameAndStudentsGroupAndBranchContainingIgnoreCase(final String name, final String studentGroup, final String branch){
        return studentRepository.findByNameAndStudentsGroupAndBranchContainingIgnoreCase(name,studentGroup,branch);
    }

    
}
