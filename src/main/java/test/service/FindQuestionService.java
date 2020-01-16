package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.QuestionDTO;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindQuestionService implements FindQuestionInterface {
@Autowired
    private QuestionRepository questionRepository;

    public List<QuestionDTO> getRandomQuestions(){
        List<QuestEntity> questEntities = questionRepository.getRandomQueries();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questEntities.forEach(question -> questionDTOS.add(mapQuestionEntityToDto(question)));
        return questionDTOS;
    }
    public QuestionDTO findByIdLike(Long id) {
        QuestEntity questEntity = questionRepository.findByIdLike(id);

        return mapQuestionEntityToDto(questEntity);
    }
    private QuestionDTO mapQuestionEntityToDto(QuestEntity questEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questEntity.getId());
        questionDTO.setName(questEntity.getName());
        return questionDTO;
    }

}
