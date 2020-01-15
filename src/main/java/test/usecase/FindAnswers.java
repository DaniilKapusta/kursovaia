package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.AnswersEntity;
import test.repository.AnswerRepository;

import java.util.List;
@Service
public class FindAnswers {
 @Autowired
    private AnswerRepository answerRepository;

    public List<AnswersEntity> findByQuestionIdLike(Long questionId){
        return answerRepository.findByQuestionIdLike(questionId);
    }

    public AnswersEntity findByIdLike(Long id) {
        return answerRepository.findByIdLike(id);
    }
}
