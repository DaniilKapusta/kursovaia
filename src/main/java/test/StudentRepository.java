package test;


import org.springframework.data.repository.CrudRepository;
import test.entity.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    public Student findByIdLike(Long id);
    public List<Student> findByNameContainingIgnoreCase(String name);
    public List<Student> findByStudentsGroupContainingIgnoreCase(String studentsGroup);
    public List<Student> findByBranchContainingIgnoreCase(String branch);
    public Student findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch);

}