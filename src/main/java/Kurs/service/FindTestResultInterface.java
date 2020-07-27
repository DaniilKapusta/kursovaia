package Kurs.service;

import Kurs.Dto.TestResultDto;

import java.sql.Date;
import java.util.List;

public interface FindTestResultInterface {
     List<TestResultDto> findByStudentIdLike(Long studentId);
     List<TestResultDto> findByTestingDate(Date testingDate);
     List<TestResultDto> findByTestIdLike(Long testId);
}
