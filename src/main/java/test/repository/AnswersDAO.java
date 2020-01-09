package test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.entity.AnswersEntity;

import java.util.List;

@Repository
public interface AnswersDAO extends CrudRepository<AnswersEntity, Long> {

    public List<AnswersEntity> findByQuestionIdLike(Long questionId);
   // public AnswersEntity findIsRight(Boolean isRight);

    public AnswersEntity findByIdLike(Long id);

}
