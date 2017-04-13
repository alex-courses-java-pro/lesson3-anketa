package com.baz.web;

import com.baz.model.QuizAnswer;
import com.baz.model.QuizQuestion;
import com.baz.model.QuizResult;
import com.baz.repository.QuizQuestionsRepositoryImpl;
import com.baz.repository.QuizQuestionsRepository;
import com.baz.repository.QuizResultsRepositoryImpl;
import com.baz.repository.QuizResultsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
//@WebServlet("/WEB-INF/index.jsp")
public class QuizServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(QuizServlet.class.getSimpleName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }


}
