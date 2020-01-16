package test.service;

import test.Dto.AnswerDto;

import java.util.List;

public interface FindAnswersInterface {
     List<AnswerDto> findByQuestionIdLike(Long questionId);
     AnswerDto findByIdLike(Long id);
}
