package com.adirtka.javaservlet;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Subject;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Метод для отображения всех предметов и удаления
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Логика для обработки данных
        // Например, получение списка предметов из базы данных
        List<Subject> subjects = null;
        try {
            subjects = DatabaseHelper.getSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Передаем список предметов в JSP через request атрибут
        request.setAttribute("subjects", subjects);

        // Перенаправляем запрос на JSP с помощью RequestDispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects.jsp");
        dispatcher.forward(request, response);
    }


    // Метод для обработки добавления и удаления предметов
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String teacher = request.getParameter("teacher");
        String faculty = request.getParameter("faculty");

        if (name != null && !name.isEmpty() && teacher != null && !teacher.isEmpty() && faculty != null && !faculty.isEmpty()) {
            // Добавление нового предмета
            try {
                DatabaseHelper.addSubject(name, teacher, faculty);
                response.sendRedirect("subjects"); // Перенаправление на страницу списка предметов
            } catch (SQLException e) {
                response.getWriter().println("Ошибка при добавлении предмета: " + e.getMessage());
            }
        }
    }


    // Удаление предмета
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            try {
                DatabaseHelper.deleteSubject(Integer.parseInt(id));
                response.sendRedirect("subjects");
            } catch (SQLException e) {
                response.getWriter().println("Ошибка при удалении предмета: " + e.getMessage());
            }
        }
    }

}
