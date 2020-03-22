package test.service;

import test.Dto.TestDTO;

import java.util.List;

public interface FindTestInterface {
    TestDTO findByIdLike(Long testId);
    List<TestDTO> findAll();
}
