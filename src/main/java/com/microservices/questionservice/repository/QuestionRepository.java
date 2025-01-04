package com.microservices.questionservice.repository;

import com.microservices.questionservice.entiy.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    @Query(value = "select q.id FROM QuestionEntity q where q.category=:category ORDER BY RANDOM() LIMIT :numberOfQuestions",
            nativeQuery = false)
    List<Integer> findQuestionsByCategory(String category, String numberOfQuestions);
}
