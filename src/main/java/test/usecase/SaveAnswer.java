package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.DTO.AnswerDTO;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

@Service
public class SaveAnswer {
    @Autowired
    private AnswerRepository answerRepository;
    public void saveAnswer(AnswerDTO answerDTO) {
        AnswersEntity answersEntity = new AnswersEntity();
        answersEntity.setBody(answerDTO.getBody());
        answersEntity.setQuestionId(answerDTO.getQuestionId());
        answersEntity.setRight(answerDTO.getRight());
        answerRepository.save(answersEntity);
    }
}
