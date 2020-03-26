package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.TestResultDto;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindTestResultService implements FindTestResultInterface {
    @Autowired
    private TestResultRepository testResultRepository;


    public List<TestResultDto> findByStudentIdLike(Long studentId) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByStudentIdLike(studentId);
        List<TestResultDto> testResultDtos = new ArrayList<>();
        testResultEntities.forEach(result -> testResultDtos.add(mapTestResultEntityToDto(result)));
        return testResultDtos;
    }

    public List<TestResultDto> findByTestingDate(Date testingDate) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByTestingDate(testingDate);
        List<TestResultDto> testResultDtos = new ArrayList<>();
        testResultEntities.forEach(result -> testResultDtos.add(mapTestResultEntityToDto(result)));
        return testResultDtos;
    }

    public List<TestResultDto> findByTestIdLike(Long testId) {
        List<TestResultEntity> testResultEntities = testResultRepository.findByTestIdLike(testId);
        List<TestResultDto> testResultDtos = new ArrayList<>();
        testResultEntities.forEach(result -> testResultDtos.add(mapTestResultEntityToDto(result)));
        return testResultDtos;
    }

    private TestResultDto mapTestResultEntityToDto(TestResultEntity testResultEntity) {
        TestResultDto testResultDTO = new TestResultDto();
        testResultDTO.setId(testResultEntity.getId());
        testResultDTO.setCorrectAnswers(testResultEntity.getCorrectAnswers());
        testResultDTO.setMark(testResultEntity.getMark());
        testResultDTO.setQuestionCount(testResultEntity.getQuestionCount());
        testResultDTO.setStudentId(testResultEntity.getStudentId());
        testResultDTO.setTestingDate(testResultEntity.getTestingDate());
        testResultDTO.setTestId(testResultEntity.getTestId());
        testResultDTO.setTestName(testResultEntity.getTestName());
        return testResultDTO;
    }
 }
