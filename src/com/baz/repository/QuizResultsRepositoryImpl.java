package com.baz.repository;

import com.baz.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizResultsRepositoryImpl implements QuizResultsRepository {

    private static QuizResultsRepositoryImpl instance;

    private List<QuizResult> results = new ArrayList<>();

    public static synchronized QuizResultsRepositoryImpl getInstance(){
        if(instance == null){
            instance = new QuizResultsRepositoryImpl();
        }
        return instance;
    }

    private QuizResultsRepositoryImpl() {
    }

    @Override
    public List<QuizResult> getQuizResults() {
        synchronized (this) {
            return results;
        }
    }

    @Override
    public void addQuizResult(QuizResult result) {
        synchronized (this) {
            results.add(result);
        }
    }
}
