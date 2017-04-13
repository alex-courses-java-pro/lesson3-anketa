package com.baz.web;

import com.baz.model.QuizAnswer;
import com.baz.model.QuizQuestion;
import com.baz.model.QuizResult;
import com.baz.repository.QuizQuestionsRepository;
import com.baz.repository.QuizQuestionsRepositoryImpl;
import com.baz.repository.QuizResultsRepository;
import com.baz.repository.QuizResultsRepositoryImpl;
import com.baz.util.AnswerStatAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by arahis on 4/13/17.
 */
@WebServlet("/result")
public class QuizStatisticServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(QuizStatisticServlet.class.getSimpleName());

    private final String NAME_FIELD_PARAMETER = "namef";

    private QuizQuestionsRepository quizQuestionsRepository;
    private QuizResultsRepository quizResultsRepository;
    private List<QuizQuestion> questions;

    @Override
    public void init() throws ServletException {
        super.init();
        //logger.info("initing question repository");
        quizQuestionsRepository = QuizQuestionsRepositoryImpl.getInstance();

        //logger.info("getting questions from repository");
        questions = quizQuestionsRepository.getQuizQuestions();

        //logger.info("initing results repository");
        quizResultsRepository = QuizResultsRepositoryImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //logger.info("getting data from form");
        Enumeration<String> parametersNames = req.getParameterNames();

        logger.info("getting quiz result for participant");
        QuizResult participantQuizResult = new QuizResult();
        List<QuizAnswer> participantQuizAnswersList = new ArrayList<>();
        List<String> questionsTextList = new ArrayList<>();
        List<String> answersTextList = new ArrayList<>();

        while (parametersNames.hasMoreElements()) {
            String parameterName = parametersNames.nextElement();
            //logger.info("parameter at the start of while: " + parameterName);

            if (parameterName.equals(NAME_FIELD_PARAMETER)) {
                String name = req.getParameter(parameterName);
                logger.info("name of participant: " + name);
                participantQuizResult.setName(name);
            } else {
                //logger.info("NOT name parameter: " + parameterName);
                if (parameterName.matches("^questionText\\d*")) {
                    //logger.info("parameter matches questionText pattern: " + parameterName);
                    String questionText = req.getParameter(parameterName);
                    logger.info("question: " + questionText);
                    questionsTextList.add(questionText);
                } else if (parameterName.matches("^question\\drb")) {
                    //logger.info("parameter matches questionrb pattern: " + parameterName);
                    String answerText = req.getParameter(parameterName);
                    logger.info("answer: " + answerText);
                    answersTextList.add(answerText);
                } else {
                    logger.warning("unknown and unexpected parameter: "
                            + req.getParameter(parameterName));
                }
            }
        }
        // not sure about code below

        // creating quiz answer for each question that was in form and setting its question text
        for (String questionText : questionsTextList) {
            QuizAnswer quizAnswer = new QuizAnswer();
            quizAnswer.setQuestionText(questionText);
            participantQuizAnswersList.add(quizAnswer);
        }
        // for every quiz answer adding answer text
        for (int i = 0; i < participantQuizAnswersList.size(); i++) {
            participantQuizAnswersList.get(i).setAnswerText(answersTextList.get(i));
        }
        // pushing result of quiz to repository
        participantQuizResult.setQuizAnswers(participantQuizAnswersList);
        logger.info("participant result: " + participantQuizResult.toString());
        quizResultsRepository.addQuizResult(participantQuizResult);
        logger.info("all results: " + quizResultsRepository.getQuizResults().toString());

        // getting all possible questions
        List<String> allQuestions = new ArrayList<>();
        List<QuizResult> allResults = quizResultsRepository.getQuizResults();
        for (QuizResult result : allResults) {
            List<QuizAnswer> participantAnswers = result.getQuizAnswersList();
            for (QuizAnswer participantAnswer : participantAnswers) {
                String questionText = participantAnswer.getQuestionText();
                if (!allQuestions.contains(questionText)) {
                    allQuestions.add(questionText);
                }
            }
        }
        List<AnswerStatAttribute> answerStatAttributes = new ArrayList<>();
        // creating answer attribute for all possible question
        for (String question : allQuestions) {
            AnswerStatAttribute answerStatAttribute = new AnswerStatAttribute();
            HashMap<String, Integer> answerStat = new HashMap<>();
            answerStatAttribute.setQuestion(question);

            for (QuizResult result : allResults) {
                List<QuizAnswer> participantAnswers = result.getQuizAnswersList();
                for (QuizAnswer participantAnswer : participantAnswers) {
                    String questionText = participantAnswer.getQuestionText();
                    String answerText = participantAnswer.getAnswerText();
                    if (answerStatAttribute.getQuestion().equals(questionText)) {
                        int count = answerStat.containsKey(answerText) ? answerStat.get(answerText) : 0;
                        answerStat.put(answerText, count + 1);
                    }
                }
            }
            answerStatAttribute.setAnswerStat(answerStat);
            answerStatAttributes.add(answerStatAttribute);
        }

        req.setAttribute("stats", answerStatAttributes);
        req.getRequestDispatcher("/WEB-INF/quiz-statistic.jsp").forward(req, resp);
    }
}
