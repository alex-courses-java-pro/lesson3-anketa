package com.baz.repository;

import com.baz.model.QuizResult;

import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public interface QuizResultsRepository {
    List<QuizResult> getQuizResults();
    void addQuizResult(QuizResult result);
}
