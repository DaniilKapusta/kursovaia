package test.service;

import test.Dto.QuestionDto;

import java.util.List;

public interface FindQuestionInterface {
    // List<QuestionDto> getRandomQuestions();
     QuestionDto findByIdLike(Long id);
     List<QuestionDto> findByTestIdLike(Long testId);
}
