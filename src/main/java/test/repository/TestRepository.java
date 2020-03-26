package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.entity.TestEntity;

import java.util.List;


@Repository
public interface TestRepository extends CrudRepository<TestEntity,Long> {
    TestEntity findByIdLike(Long id);
    List<TestEntity> findAll();
}
