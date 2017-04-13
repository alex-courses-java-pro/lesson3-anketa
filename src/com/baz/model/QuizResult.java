package com.baz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arahis on 4/13/17.
 */
public class QuizResult implements Serializable{
    private String name;
    private List<QuizAnswer> answers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "name='" + name + '\'' +
                ", answers=" + answers.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuizResult that = (QuizResult) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return answers != null ? answers.equals(that.answers) : that.answers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
