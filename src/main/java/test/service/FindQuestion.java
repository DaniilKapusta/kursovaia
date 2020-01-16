package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.QuestionDTO;
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
    public QuestionDTO findByIdLike(Long id) {
        QuestEntity questEntity = questionRepository.findByIdLike(id);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questEntity.getId());
        questionDTO.setName(questEntity.getName());

        return questionDTO;
    }

}
