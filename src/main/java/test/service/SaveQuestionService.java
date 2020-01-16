package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.QuestionDTO;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

@Service
public class SaveQuestionService implements SaveQuestionInterface {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        QuestEntity questEntity = new QuestEntity();
        questEntity.setName(questionDTO.getName());
        questEntity =  questionRepository.save(questEntity);
        questionDTO.setId(questEntity.getId());
      return questionDTO;
    }
}
