package com.baz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizQuestion implements Serializable {
    private String question;
    private List<String> answers;

    public QuizQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                ", answers:" + answers.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (!question.equals(that.question)) return false;
        return answers.equals(that.answers);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answers.hashCode();
        return result;
    }
}
