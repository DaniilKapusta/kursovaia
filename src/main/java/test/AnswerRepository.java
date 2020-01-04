package test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.entity.AnswersEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswersEntity, Long> {
}

