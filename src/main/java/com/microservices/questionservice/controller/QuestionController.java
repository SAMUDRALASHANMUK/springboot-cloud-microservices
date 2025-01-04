package com.microservices.questionservice.controller;

import com.microservices.questionservice.entiy.QuestionEntity;
import com.microservices.questionservice.model.QuestionDTO;
import com.microservices.questionservice.model.QuizQuestionDTO;
import com.microservices.questionservice.model.UserResponse;
import com.microservices.questionservice.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("get-question")
    public ResponseEntity<List<QuestionDTO>> getQuestionsById(
            @RequestBody List<Integer> questionIds
    ) {
        return new ResponseEntity<>(questionService.getQuestionByIds(questionIds), HttpStatus.OK);
    }

    @PostMapping("create-question")
    public ResponseEntity<String> createQuestion(
            @RequestBody List<QuestionEntity> questionEntityList
    ) {
        return new ResponseEntity<>(questionService.createQuestion(questionEntityList), HttpStatus.OK);
    }

    @PostMapping("calculate-result")
    public ResponseEntity<String> calculateScore(
            @RequestBody List<UserResponse> userResponses
    ) {
        return new ResponseEntity<>(questionService.calculateResult(userResponses), HttpStatus.OK);
    }

    @PostMapping("create-questions-for-quiz")
    public ResponseEntity<List<Integer>> createQuestionBasedOnCategory(
            @RequestBody QuizQuestionDTO quizQuestionDTO) {
        return new ResponseEntity<>(questionService.createQuestionsBasedOnCategory(quizQuestionDTO), HttpStatus.OK);

    }
}
