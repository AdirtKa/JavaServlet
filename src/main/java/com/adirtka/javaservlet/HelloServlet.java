package com.adirtka.javaservlet;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/subject")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Логика для обработки данных
        // Например, получение списка предметов из базы данных
        List<Subject> subjects = null;
        try {
            subjects = DatabaseHelper.getSubjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Проверяем, что данные не null
        if (subjects == null) {
            subjects = new ArrayList<>(); // если список пустой, создаем пустой список
        }

        // Передаем список предметов в JSP через request атрибут
        request.setAttribute("subjects", subjects);

        // Перенаправляем запрос на JSP с помощью RequestDispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects.jsp");
        dispatcher.forward(request, response);
    }
}
