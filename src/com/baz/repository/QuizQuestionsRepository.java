package com.baz.repository;

import com.baz.model.QuizQuestion;

import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public interface QuizQuestionsRepository {

    List<QuizQuestion> getQuizQuestions();
}
