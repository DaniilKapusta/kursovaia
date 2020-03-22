package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.TestResultDto;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

@Service
public class SaveTestResultService implements SaveTestResultInterface {
    @Autowired
    private TestResultRepository testResultRepository;

    public void saveTestResult(TestResultDto testResultDTO) {
        TestResultEntity testResultEntity = new TestResultEntity();
        testResultEntity.setCorrectAnswers(testResultDTO.getCorrectAnswers());
        testResultEntity.setMark(testResultDTO.getMark());
        testResultEntity.setQuestionCount(testResultDTO.getQuestionCount());
        testResultEntity.setStudentId(testResultDTO.getStudentId());
        testResultEntity.setTestingDate(testResultDTO.getTestingDate());
        testResultEntity.setTestId(testResultDTO.getTestId());
        testResultEntity.setTestName(testResultDTO.getTestName());
       testResultRepository.save(testResultEntity);
    }
}
