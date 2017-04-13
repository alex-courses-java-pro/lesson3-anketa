package com.baz.model;

import java.io.Serializable;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizAnswer implements Serializable {
    private String question;
    private String answer;

    public QuizAnswer() {
    }

    public QuizAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizAnswer that = (QuizAnswer) o;

        if (!question.equals(that.question)) return false;
        return answer != null ? answer.equals(that.answer) : that.answer == null;
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
