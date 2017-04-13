package com.baz.repository;

import com.baz.model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizQuestionsRepositoryImpl implements QuizQuestionsRepository {

    private static QuizQuestionsRepositoryImpl instance;

    private List<QuizQuestion> questions = new ArrayList<>();

    public static synchronized QuizQuestionsRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new QuizQuestionsRepositoryImpl();
        }
        return instance;
    }

    private QuizQuestionsRepositoryImpl() {
        synchronized (this) {
            //answers will be same
            List<String> answers = new ArrayList<>();
            answers.add("Yes");
            answers.add("No");
            answers.add("Maybe");

            QuizQuestion question1 = new QuizQuestion();
            question1.setQuestionText("Do you like pointless questions?");
            question1.setAnswersText(answers);

            QuizQuestion question2 = new QuizQuestion();
            question2.setQuestionText("Do you like quizzes?");
            question2.setAnswersText(answers);

            QuizQuestion question3 = new QuizQuestion();
            question3.setQuestionText("Do you regret of wasted time doing this quiz?");
            question3.setAnswersText(answers);

            questions.add(question1);
            questions.add(question2);
            questions.add(question3);
        }
    }

    @Override
    public List<QuizQuestion> getQuizQuestions() {
        synchronized (this) {
            return questions;
        }
    }


}
