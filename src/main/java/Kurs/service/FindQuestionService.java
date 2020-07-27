package Kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Kurs.Dto.QuestionDto;
import Kurs.entity.QuestEntity;
import Kurs.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public final class FindQuestionService implements FindQuestionInterface {
@Autowired
    private QuestionRepository questionRepository;

  /*  public List<QuestionDto> getRandomQuestions(){
        List<QuestEntity> questEntities = questionRepository.getRandomQueries();
        List<QuestionDto> questionDtos = new ArrayList<>();
        questEntities.forEach(question -> questionDtos.add(mapQuestionEntityToDto(question)));
        return questionDtos;
    }
    */

    public QuestionDto findByIdLike(Long id) {
        QuestEntity questEntity = questionRepository.findByIdLike(id);

        return mapQuestionEntityToDto(questEntity);
    }
    public List<QuestionDto> findByTestIdLike(Long testId) {
        List<QuestEntity> questEntities = questionRepository.findByTestIdLike(testId);
        List<QuestionDto> questionDtos = new ArrayList<>();
        questEntities.forEach(questEntity -> questionDtos.add(mapQuestionEntityToDto(questEntity)));
        return questionDtos;
    }
    private QuestionDto mapQuestionEntityToDto(QuestEntity questEntity) {
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setId(questEntity.getId());
        questionDTO.setName(questEntity.getName());
        return questionDTO;
    }

}
