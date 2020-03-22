package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.QuestionDto;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

@Service
public class SaveQuestionService implements SaveQuestionInterface {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDto saveQuestion(QuestionDto questionDTO) {
        QuestEntity questEntity = new QuestEntity();
        questEntity.setName(questionDTO.getName());
        questEntity =  questionRepository.save(questEntity);
        questEntity.setTestId(questionDTO.getTestId());
        questionDTO.setId(questEntity.getId());
      return questionDTO;
    }
}
