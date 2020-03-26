package test.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.entity.QuestEntity;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<QuestEntity, Long> {

     QuestEntity findByIdLike(Long id);
 //   @Query(value = "SELECT * FROM QUEST ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
  //  List<QuestEntity> getRandomQueries();
    List<QuestEntity> findByTestIdLike(Long testId);


}