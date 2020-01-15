package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

@Service
public class SaveQuestion {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestEntity saveQuestion(QuestEntity questEntity) {
      return questionRepository.save(questEntity);
    }
}
