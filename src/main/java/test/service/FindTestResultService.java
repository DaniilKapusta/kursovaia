package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.TestResultDTO;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindTestResultService implements FindTestResultInterface {
    @Autowired
    private TestResultRepository testResultRepository;


    public List<TestResultDTO> findByStudentIdLike(Long studentId) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByStudentIdLike(studentId);
        List<TestResultDTO> testResultDTOS = new ArrayList<>();
        testResultEntities.forEach(result -> testResultDTOS.add(mapTestResultEntityToDto(result)));
        return testResultDTOS;
    }

    public List<TestResultDTO> findByTestingDate(Date testingDate) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByTestingDate(testingDate);
        List<TestResultDTO> testResultDTOS = new ArrayList<>();
        testResultEntities.forEach(result -> testResultDTOS.add(mapTestResultEntityToDto(result)));
        return testResultDTOS;
    }

    private TestResultDTO mapTestResultEntityToDto(TestResultEntity testResultEntity) {
        TestResultDTO testResultDTO = new TestResultDTO();
        testResultDTO.setId(testResultEntity.getId());
        testResultDTO.setCorrectAnswers(testResultEntity.getCorrectAnswers());
        testResultDTO.setMark(testResultEntity.getMark());
        testResultDTO.setQuestionCount(testResultEntity.getQuestionCount());
        testResultDTO.setStudentId(testResultEntity.getStudentId());
        testResultDTO.setTestingDate(testResultEntity.getTestingDate());
        return testResultDTO;
    }
 }
