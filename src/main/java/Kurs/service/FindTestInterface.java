package Kurs.service;

import Kurs.Dto.TestDTO;

import java.util.List;

public interface FindTestInterface {
    TestDTO findByIdLike(Long testId);
    List<TestDTO> findAll();
}
