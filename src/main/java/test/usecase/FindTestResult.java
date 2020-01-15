package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

import java.sql.Date;
import java.util.List;

@Service
public class FindTestResult {
    @Autowired
    private TestResultRepository testResultRepository;


    public List<TestResultEntity> findByStudentIdLike(Long studentId) {
        return testResultRepository.findByStudentIdLike(studentId);
    }

    public List<TestResultEntity> findByTestingDate(Date testingDate) {
        return testResultRepository.findByTestingDate(testingDate);
    }
 }
