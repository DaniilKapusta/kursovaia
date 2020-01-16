package test.service;

import test.DTO.QuestionDTO;

import java.util.List;

public interface FindQuestionInterface {
     List<QuestionDTO> getRandomQuestions();
     QuestionDTO findByIdLike(Long id);
}
