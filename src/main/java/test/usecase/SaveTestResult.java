package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.TestResultEntity;
import test.repository.TestResultRepository;

@Service
public class SaveTestResult {
    @Autowired
    private TestResultRepository testResultRepository;
    public TestResultEntity saveTestResult(TestResultEntity testResultEntity) {
        return testResultRepository.save(testResultEntity);
    }
}
