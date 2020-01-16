package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.TestResultDTO;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

@Service
public class SaveTestResult {
    @Autowired
    private TestResultRepository testResultRepository;

    public void saveTestResult(TestResultDTO testResultDTO) {
        TestResultEntity testResultEntity = new TestResultEntity();
        testResultEntity.setCorrectAnswers(testResultDTO.getCorrectAnswers());
        testResultEntity.setMark(testResultDTO.getMark());
        testResultEntity.setQuestionCount(testResultDTO.getQuestionCount());
        testResultEntity.setStudentId(testResultDTO.getStudentId());
        testResultEntity.setTestingDate(testResultDTO.getTestingDate());
       testResultRepository.save(testResultEntity);
    }
}
