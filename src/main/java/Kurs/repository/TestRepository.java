package Kurs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Kurs.entity.TestEntity;

import java.util.List;


@Repository
public interface TestRepository extends CrudRepository<TestEntity,Long> {
    TestEntity findByIdLike(Long id);
    List<TestEntity> findAll();
}
