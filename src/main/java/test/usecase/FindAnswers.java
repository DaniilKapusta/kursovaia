package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.AnswerDTO;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class FindAnswers {
 @Autowired
    private AnswerRepository answerRepository;

    public List<AnswerDTO> findByQuestionIdLike(Long questionId){
        List<AnswersEntity> answersEntities = answerRepository.findByQuestionIdLike(questionId);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        answersEntities.forEach(answer -> {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setId(answer.getId());
            answerDTO.setBody(answer.getBody());
            answerDTO.setQuestionId(answer.getQuestionId());
            answerDTO.setRight(answer.getRight());
            answerDTOS.add(answerDTO);
        });
        return answerDTOS;
    }

    public AnswersEntity findByIdLike(Long id) {
        return answerRepository.findByIdLike(id);
    }
}
