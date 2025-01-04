package com.microservices.questionservice.service;

import com.microservices.questionservice.entiy.QuestionEntity;
import com.microservices.questionservice.model.QuestionDTO;
import com.microservices.questionservice.model.QuizQuestionDTO;
import com.microservices.questionservice.model.UserResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getQuestionByIds(List<Integer> questionIds);

    String createQuestion(List<QuestionEntity> questionEntityList);

    String calculateResult(List<UserResponse> userResponses);

    List<Integer> createQuestionsBasedOnCategory(QuizQuestionDTO quizQuestionDTO);
}
