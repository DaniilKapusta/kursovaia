package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.AnswerDTO;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class FindAnswersService implements FindAnswersInterface {
 @Autowired
    private AnswerRepository answerRepository;

    public List<AnswerDTO> findByQuestionIdLike(Long questionId){
        List<AnswersEntity> answersEntities = answerRepository.findByQuestionIdLike(questionId);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        answersEntities.forEach(answer -> answerDTOS.add(mapEntityAnswerToDto(answer)));
        return answerDTOS;
    }

    public AnswerDTO findByIdLike(Long id) {
        return mapEntityAnswerToDto(answerRepository.findByIdLike(id));
    }

    private AnswerDTO mapEntityAnswerToDto(AnswersEntity answersEntity) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answersEntity.getId());
        answerDTO.setRight(answersEntity.getRight());
        answerDTO.setQuestionId(answersEntity.getQuestionId());
        answerDTO.setBody(answersEntity.getBody());
        return answerDTO;
    }
}
