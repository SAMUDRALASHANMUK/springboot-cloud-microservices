package com.microservices.questionservice.service.impl;

import com.microservices.questionservice.entiy.QuestionEntity;
import com.microservices.questionservice.model.QuestionDTO;
import com.microservices.questionservice.model.QuizQuestionDTO;
import com.microservices.questionservice.model.UserResponse;
import com.microservices.questionservice.repository.QuestionRepository;
import com.microservices.questionservice.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDTO> getQuestionByIds(List<Integer> questionIds) {
        List<QuestionEntity> question = questionRepository.findAllById(questionIds);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (QuestionEntity questionEntity : question) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(questionEntity.getId());
            questionDTO.setQuestion(questionEntity.getQuestion());
            questionDTO.setOption1(questionEntity.getOption1());
            questionDTO.setOption2(questionEntity.getOption2());
            questionDTO.setOption3(questionEntity.getOption3());
            questionDTO.setOption4(questionEntity.getOption4());
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    @Override
    public String createQuestion(List<QuestionEntity> questionEntityList) {
        questionRepository.saveAll(questionEntityList);
        return "success";
    }

    @Override
    public String calculateResult(List<UserResponse> userResponses) {
        Integer result = 0;
        List<QuestionEntity> questionEntityList = questionRepository.findAll();
        int i = 0;
        for (UserResponse userResponse : userResponses) {
            QuestionEntity question = questionEntityList.get(i);
            if (question.getId().equals(userResponse.getId()))
                if (question.getAnswer().equals(userResponse.getResponse()))
                    result++;
            i++;
        }
        return String.format("Hey user you got %d marks", result);
    }

    @Override
    public List<Integer> createQuestionsBasedOnCategory(QuizQuestionDTO quizQuestionDTO) {
        return questionRepository.findQuestionsByCategory(
                quizQuestionDTO.getCategory(), quizQuestionDTO.getNumberOfQuestions());
    }
}
