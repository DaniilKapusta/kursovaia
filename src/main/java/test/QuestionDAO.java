package test;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.entity.QuestEntity;

@Repository
public interface QuestionDAO extends CrudRepository<QuestEntity, Long> {
    public QuestEntity findByNameLike(String name);

    public QuestEntity findByIdLike(Long id);

}
