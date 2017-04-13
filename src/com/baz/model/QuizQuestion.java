package com.baz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizQuestion implements Serializable {
    private String questionText;
    private List<String> answersTextList;

    public QuizQuestion() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswersTextList() {
        return answersTextList;
    }

    public void setAnswersText(List<String> answersTextList) {
        this.answersTextList = answersTextList;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "questionText='" + questionText + '\'' +
                ", answersTextList:" + answersTextList.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizQuestion that = (QuizQuestion) o;

        if (!questionText.equals(that.questionText)) return false;
        return answersTextList.equals(that.answersTextList);
    }

    @Override
    public int hashCode() {
        int result = questionText.hashCode();
        result = 31 * result + answersTextList.hashCode();
        return result;
    }
}
