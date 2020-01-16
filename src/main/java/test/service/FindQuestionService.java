package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.QuestionDto;
import test.entity.QuestEntity;
import test.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindQuestionService implements FindQuestionInterface {
@Autowired
    private QuestionRepository questionRepository;

    public List<QuestionDto> getRandomQuestions(){
        List<QuestEntity> questEntities = questionRepository.getRandomQueries();
        List<QuestionDto> questionDtos = new ArrayList<>();
        questEntities.forEach(question -> questionDtos.add(mapQuestionEntityToDto(question)));
        return questionDtos;
    }
    public QuestionDto findByIdLike(Long id) {
        QuestEntity questEntity = questionRepository.findByIdLike(id);

        return mapQuestionEntityToDto(questEntity);
    }
    private QuestionDto mapQuestionEntityToDto(QuestEntity questEntity) {
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setId(questEntity.getId());
        questionDTO.setName(questEntity.getName());
        return questionDTO;
    }

}
