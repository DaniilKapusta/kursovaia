package test.service;

import test.DTO.TestResultDTO;

import java.sql.Date;
import java.util.List;

public interface FindTestResultInterface {
     List<TestResultDTO> findByStudentIdLike(Long studentId);
     List<TestResultDTO> findByTestingDate(Date testingDate);
}
