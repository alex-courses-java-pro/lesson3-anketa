package com.baz.model;

import java.io.Serializable;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizAnswer implements Serializable {
    private String questionText;
    private String answerText;

    public QuizAnswer() {
    }

    public QuizAnswer(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "questionText='" + questionText + '\'' +
                ", answerText='" + answerText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizAnswer that = (QuizAnswer) o;

        if (!questionText.equals(that.questionText)) return false;
        return answerText != null ? answerText.equals(that.answerText) : that.answerText == null;
    }

    @Override
    public int hashCode() {
        int result = questionText.hashCode();
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        return result;
    }
}
