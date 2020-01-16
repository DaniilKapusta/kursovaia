package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.AnswerDto;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class FindAnswersService implements FindAnswersInterface {
 @Autowired
    private AnswerRepository answerRepository;

    public List<AnswerDto> findByQuestionIdLike(Long questionId){
        List<AnswersEntity> answersEntities = answerRepository.findByQuestionIdLike(questionId);
        List<AnswerDto> answerDtos = new ArrayList<>();
        answersEntities.forEach(answer -> answerDtos.add(mapEntityAnswerToDto(answer)));
        return answerDtos;
    }

    public AnswerDto findByIdLike(Long id) {
        return mapEntityAnswerToDto(answerRepository.findByIdLike(id));
    }

    private AnswerDto mapEntityAnswerToDto(AnswersEntity answersEntity) {
        AnswerDto answerDTO = new AnswerDto();
        answerDTO.setId(answersEntity.getId());
        answerDTO.setRight(answersEntity.getRight());
        answerDTO.setQuestionId(answersEntity.getQuestionId());
        answerDTO.setBody(answersEntity.getBody());
        return answerDTO;
    }
}
