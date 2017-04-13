package com.baz.web;

import com.baz.model.QuizQuestion;
import com.baz.repository.QuizQuestionsRepositoryImpl;
import com.baz.repository.QuizQuestionsRepository;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by arahis on 4/12/17.
 */
@WebServlet("/index.jsp")
public class InitRedirectServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(InitRedirectServlet.class.getSimpleName());

    private QuizQuestionsRepository quizQuestionsRepository;

    @Override
    public void init() throws ServletException {
        super.init();

        logger.info("initing question repository");
        quizQuestionsRepository = QuizQuestionsRepositoryImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.info("fetching quiz questions");
        List<QuizQuestion> questions = quizQuestionsRepository.getQuizQuestions();

        logger.info(questions.toString());
        req.setAttribute("questions", questions);

        logger.info("forwarding questions to  /WEB-INF/index.jsp");
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

}
