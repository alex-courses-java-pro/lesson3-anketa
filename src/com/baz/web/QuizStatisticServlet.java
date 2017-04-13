package com.baz.web;

import com.baz.model.QuizAnswer;
import com.baz.model.QuizQuestion;
import com.baz.model.QuizResult;
import com.baz.repository.QuizQuestionsRepository;
import com.baz.repository.QuizQuestionsRepositoryImpl;
import com.baz.repository.QuizResultsRepository;
import com.baz.repository.QuizResultsRepositoryImpl;

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

    //private QuizResultsRepository quizResultsRepository;

    private final String NAME_FIELD_PARAMETER = "namef";

    private QuizQuestionsRepository quizQuestionsRepository;
    private QuizResultsRepository quizResultsRepository;
    private List<QuizQuestion> questions;

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("initing question repository");
        quizQuestionsRepository = QuizQuestionsRepositoryImpl.getInstance();

        logger.info("getting questions from repository");
        questions = quizQuestionsRepository.getQuizQuestions();

        logger.info("initing results repository");
        quizResultsRepository = QuizResultsRepositoryImpl.getInstance();
    }

    /*@Override
    public void init() throws ServletException {
        super.init();

        logger.info("initing results repository");
        quizResultsRepository = QuizResultsRepositoryImpl.getInstance();
    }*/

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("fetching quiz results");
        List<QuizResult> results = quizResultsRepository.getQuizResults();

        logger.info(results.toString());

        logger.info("forwarding questions to  /WEB-INF/quiz-statistic.jsp");
        req.getRequestDispatcher("/WEB-INF/quiz-statistic.jsp").forward(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("getting data from form");
        Enumeration<String> parametersNames = req.getParameterNames();

        QuizResult result = new QuizResult();
        List<QuizAnswer> participantAnswers = new ArrayList<>();

        int questionIndex = 0;
        while (parametersNames.hasMoreElements()) {
            String parameterName = parametersNames.nextElement();

            if (parameterName.equals(NAME_FIELD_PARAMETER)) {
                String name = req.getParameter(parameterName);
                logger.info("name of participant: " + name);
                result.setName(name);
            } else {
                QuizAnswer answer = new QuizAnswer();
                QuizQuestion q = questions.get(questionIndex);
                logger.info("question: " + q.getQuestion());
                answer.setQuestion(q.getQuestion());
                int answerIndex = Integer.parseInt(req.getParameter(parameterName));
                logger.info("chosen answer: " + q.getAnswers().get(answerIndex));
                answer.setAnswer(q.getAnswers().get(answerIndex));
                participantAnswers.add(answer);
                questionIndex++;
            }
        }
        result.setAnswers(participantAnswers);
        logger.info("quiz result: " + result.toString());

        quizResultsRepository.addQuizResult(result);


        logger.info("fetching quiz results");
        List<QuizResult> results = quizResultsRepository.getQuizResults();

        logger.info(results.toString());

        List<String> allQuestions = new ArrayList<>();
        for (QuizQuestion q : questions)
            allQuestions.add(q.getQuestion());

        HashMap<String, Integer> answersStat = new HashMap<>();
        for (QuizResult r : results) {
            List<QuizAnswer> answersList = r.getAnswers();
            for (QuizAnswer a : answersList) {
                String answerString = a.getAnswer();
                int count = answersStat.containsKey(answerString) ? answersStat.get(answerString) : 0;
                answersStat.put(answerString, count + 1);
            }
        }

        req.setAttribute("questions", allQuestions);
        req.setAttribute("answerStat", answersStat);
        req.getRequestDispatcher("/WEB-INF/quiz-statistic.jsp").forward(req, resp);
    }
}
