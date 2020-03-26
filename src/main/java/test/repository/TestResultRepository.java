package test.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.entity.TestResultEntity;

import java.sql.Date;
import java.util.List;

@Repository
public interface TestResultRepository extends CrudRepository<TestResultEntity,Long> {
    List<TestResultEntity> findByStudentIdLike(Long studentId);

    List<TestResultEntity> findByTestingDate(Date testingDate);
    List<TestResultEntity> findByTestIdLike(Long testId);

}
