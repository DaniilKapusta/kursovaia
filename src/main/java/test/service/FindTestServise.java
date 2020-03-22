package test.service;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.TestDTO;
import test.entity.TestEntity;
import test.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindTestServise implements FindTestInterface {
    @Autowired
    private TestRepository testRepository;
    public TestDTO findByIdLike(Long testId) {
       return mapEntityToDTO(testRepository.findByIdLike(testId));
    }
   public List<TestDTO> findAll() {
        List<TestDTO> testDTOS = new ArrayList<>();
        testRepository.findAll().forEach(testEntity -> testDTOS.add(mapEntityToDTO(testEntity)));
   return testDTOS;
    }
    private TestDTO mapEntityToDTO(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setName(testEntity.getTestName());
        testDTO.setQuestionCount(testEntity.getQuestionCount());
        return testDTO;
    }

}
