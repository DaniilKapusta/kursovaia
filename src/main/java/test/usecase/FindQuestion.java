package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

import java.util.List;
@Service
public final class FindQuestion {
@Autowired
    private QuestionRepository questionRepository;
    public List<QuestEntity> getRandomQuestions(){
        return questionRepository.getRandomQueries();
    }
    public QuestEntity findByIdLike(Long id) {
        return questionRepository.findByIdLike(id);
    }

}
