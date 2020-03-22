package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.TestDTO;
import test.entity.TestEntity;
import test.repository.TestRepository;

@Service
public final class SaveTestService implements SaveTestInterface {
    @Autowired
    private TestRepository testRepository;
    public TestDTO saveTest (TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setTestName(testDTO.getName());
        testEntity.setQuestionCount(testDTO.getQuestionCount());
        testDTO.setId(testRepository.save(testEntity).getId());
        return testDTO;
    }
}
