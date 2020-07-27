package Kurs.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Kurs.entity.Student;

import java.util.List;
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
     Student findByIdLike(Long id);
     List<Student> findByNameContainingIgnoreCase(String name);
     List<Student> findByStudentsGroupContainingIgnoreCase(String studentsGroup);
     List<Student> findByBranchContainingIgnoreCase(String branch);
     Student findByNameAndStudentsGroupAndBranchContainingIgnoreCase(String name, String studentGroup, String branch);

}