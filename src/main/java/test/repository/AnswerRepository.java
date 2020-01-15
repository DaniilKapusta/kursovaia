package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.entity.AnswersEntity;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswersEntity, Long> {
     List<AnswersEntity> findByQuestionIdLike(Long questionId);

     AnswersEntity findByIdLike(Long id);
}

