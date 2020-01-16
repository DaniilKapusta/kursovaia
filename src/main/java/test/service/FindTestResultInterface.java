package test.service;

import test.Dto.TestResultDto;

import java.sql.Date;
import java.util.List;

public interface FindTestResultInterface {
     List<TestResultDto> findByStudentIdLike(Long studentId);
     List<TestResultDto> findByTestingDate(Date testingDate);
}
