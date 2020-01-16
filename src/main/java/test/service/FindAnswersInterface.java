package test.service;

import test.DTO.AnswerDTO;

import java.util.List;

public interface FindAnswersInterface {
     List<AnswerDTO> findByQuestionIdLike(Long questionId);
     AnswerDTO findByIdLike(Long id);
}
