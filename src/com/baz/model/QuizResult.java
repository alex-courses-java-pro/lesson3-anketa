package com.baz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizResult implements Serializable{
    private String name;
    private List<QuizAnswer> quizAnswersList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuizAnswer> getQuizAnswersList() {
        return quizAnswersList;
    }

    public void setQuizAnswers(List<QuizAnswer> quizAnswersList) {
        this.quizAnswersList = quizAnswersList;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "name='" + name + '\'' +
                ", quizAnswersList=" + quizAnswersList.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizResult that = (QuizResult) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return quizAnswersList != null ? quizAnswersList.equals(that.quizAnswersList) : that.quizAnswersList == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (quizAnswersList != null ? quizAnswersList.hashCode() : 0);
        return result;
    }
}
