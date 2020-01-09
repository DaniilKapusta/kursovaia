package test.usecase;

import test.entity.Student;
import test.repository.StudentRepository;

public final class SaveStudent {
    private StudentRepository studentRepository;
    public Student saveStudent(Student student) {
       return studentRepository.save(student);
    }
}
