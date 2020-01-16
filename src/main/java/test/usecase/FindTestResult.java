package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.TestResultDTO;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindTestResult {
    @Autowired
    private TestResultRepository testResultRepository;


    public List<TestResultDTO> findByStudentIdLike(Long studentId) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByStudentIdLike(studentId);
        List<TestResultDTO> testResultDTOS = new ArrayList<>();
        testResultEntities.forEach(result -> {
            TestResultDTO testResultDTO = new TestResultDTO();
            testResultDTO.setId(result.getId());
            testResultDTO.setCorrectAnswers(result.getCorrectAnswers());
            testResultDTO.setMark(result.getMark());
            testResultDTO.setQuestionCount(result.getQuestionCount());
            testResultDTO.setStudentId(result.getStudentId());
            testResultDTO.setTestingDate(result.getTestingDate());
            testResultDTOS.add(testResultDTO);
        });
        return testResultDTOS;
    }

    public List<TestResultDTO> findByTestingDate(Date testingDate) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByTestingDate(testingDate);
        List<TestResultDTO> testResultDTOS = new ArrayList<>();
        testResultEntities.forEach(result -> {
            TestResultDTO testResultDTO = new TestResultDTO();
            testResultDTO.setId(result.getId());
            testResultDTO.setCorrectAnswers(result.getCorrectAnswers());
            testResultDTO.setMark(result.getMark());
            testResultDTO.setQuestionCount(result.getQuestionCount());
            testResultDTO.setStudentId(result.getStudentId());
            testResultDTO.setTestingDate(result.getTestingDate());
            testResultDTOS.add(testResultDTO);
        });
        return testResultDTOS;
    }
 }
