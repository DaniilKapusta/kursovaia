package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.Dto.AnswerDto;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

@Service
public class SaveAnswerService implements SaveAnswerInterface {
    @Autowired
    private AnswerRepository answerRepository;

    public void saveAnswer(AnswerDto answerDTO) {
        AnswersEntity answersEntity = new AnswersEntity();
        answersEntity.setBody(answerDTO.getBody());
        answersEntity.setQuestionId(answerDTO.getQuestionId());
        answersEntity.setRight(answerDTO.getRight());
        answerRepository.save(answersEntity);
    }
}
