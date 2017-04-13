package com.baz.util;

import java.util.HashMap;

/**
 * Created by arahis on 4/13/17.
 */
public class AnswerStatAttribute {
    private String question;
    private HashMap<String, Integer> answerStat;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, Integer> getAnswerStat() {
        return answerStat;
    }

    public void setAnswerStat(HashMap<String, Integer> answerStat) {
        this.answerStat = answerStat;
    }
}
