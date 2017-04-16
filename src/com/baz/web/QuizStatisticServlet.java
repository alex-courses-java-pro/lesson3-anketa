package com.baz.web;

import com.baz.model.QuizAnswer;
import com.baz.model.QuizQuestion;
import com.baz.model.QuizResult;
import com.baz.repository.QuizQuestionsRepository;
import com.baz.repository.QuizQuestionsRepositoryImpl;
import com.baz.repository.QuizResultsRepository;
import com.baz.repository.QuizResultsRepositoryImpl;
import com.baz.model.AnswerStatAttribute;

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
        logger.info("getting data from form");

        QuizResult participantQuizResult = getParticipantQuizResult(req);
        logger.info("participant result: " + participantQuizResult.toString());

        quizResultsRepository.addQuizResult(participantQuizResult);

        List<QuizResult> allResults = quizResultsRepository.getQuizResults();

        List<AnswerStatAttribute> answerStatAttributes = new ArrayList<>();
        List<String> allQuestions = getAllQuestionsTextList(allResults);

        // creating answer attribute for all possible question
        for (String question : allQuestions) {
            AnswerStatAttribute answerStatAttribute = new AnswerStatAttribute();
            answerStatAttribute.setQuestion(question);

            HashMap<String, Integer> answerStat = getAnswerStatForQuestion(question, allResults);
            answerStatAttribute.setAnswerStat(answerStat);

            answerStatAttributes.add(answerStatAttribute);
        }

        req.setAttribute("stats", answerStatAttributes);
        req.getRequestDispatcher("/WEB-INF/quiz-statistic.jsp").forward(req, resp);
    }

    private HashMap<String, Integer> getAnswerStatForQuestion(String questionText,
                                                              List<QuizResult> allResults) {
        HashMap<String, Integer> answerStat = new HashMap<>();

        for (QuizResult result : allResults) {
            List<QuizAnswer> participantAnswers = result.getQuizAnswersList();
            for (QuizAnswer participantAnswer : participantAnswers) {
                String qt = participantAnswer.getQuestionText();
                String answerText = participantAnswer.getAnswerText();
                if (questionText.equals(qt)) {
                    int count = answerStat.getOrDefault(answerText, 0);
                    answerStat.put(answerText, count + 1);
                }
            }
        }
        return answerStat;
    }

    private QuizResult getParticipantQuizResult(HttpServletRequest req) {
        logger.info("getting quiz result for participant");
        QuizResult participantQuizResult = new QuizResult();

        List<QuizAnswer> participantQuizAnswers = new ArrayList<>();
        Enumeration<String> parametersNames = req.getParameterNames();

        while (parametersNames.hasMoreElements()) {
            String parameterName = parametersNames.nextElement();

            if (parameterName.equals(NAME_FIELD_PARAMETER)) {
                String name = req.getParameter(parameterName);
                logger.info("name of participant: " + name);
                participantQuizResult.setName(name);
            } else if (parameterName.matches("^questionText\\d+")) {
                String questionText = req.getParameter(parameterName);
                logger.info("question: " + questionText);

                String questionIndexString = parameterName.replaceAll("\\D+", "");
                String answerParameter = String.format("question%srb", questionIndexString);
                String answerText = req.getParameter(answerParameter);
                logger.info("answer: " + answerText);

                participantQuizAnswers.add(new QuizAnswer(questionText, answerText));
            } else {
                logger.warning("unknown and unexpected parameter: "
                        + req.getParameter(parameterName));
            }
        }
        participantQuizResult.setQuizAnswers(participantQuizAnswers);
        return participantQuizResult;
    }

    private List<String> getAllQuestionsTextList(List<QuizResult> quizResults) {
        List<String> allQuestions = new ArrayList<>();

        for (QuizResult result : quizResults) {
            List<QuizAnswer> participantAnswers = result.getQuizAnswersList();
            for (QuizAnswer participantAnswer : participantAnswers) {
                String questionText = participantAnswer.getQuestionText();
                if (!allQuestions.contains(questionText)) {
                    allQuestions.add(questionText);
                }
            }
        }
        return allQuestions;
    }
}
