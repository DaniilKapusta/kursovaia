package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

@Service
public class SaveAnswer {
    @Autowired
    private AnswerRepository answerRepository;
    public AnswersEntity saveAnswer(AnswersEntity answersEntity) {
        return answerRepository.save(answersEntity);
    }
}
