package test.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.AnswersEntity;
import test.repository.AnswersDAO;

import java.util.List;
@Service
public class FindAnswers {
 @Autowired
    private AnswersDAO answersDAO;

    public List<AnswersEntity> findByQuestionIdLike(Long questionId){
        return answersDAO.findByQuestionIdLike(questionId);
    }

    public AnswersEntity findByIdLike(Long id) {
        return answersDAO.findByIdLike(id);
    }
}
