package Kurs.service;

import Kurs.Dto.AnswerDto;

import java.util.List;

public interface FindAnswersInterface {
     List<AnswerDto> findByQuestionIdLike(Long questionId);
     AnswerDto findByIdLike(Long id);
}
