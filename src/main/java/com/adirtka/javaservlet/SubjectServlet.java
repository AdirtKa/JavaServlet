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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjects = null;
        try {
            subjects = DatabaseHelper.getSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("subjects", subjects);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String teacher = request.getParameter("teacher");
        String faculty = request.getParameter("faculty");

        if (name != null && !name.isEmpty() && teacher != null && !teacher.isEmpty() && faculty != null && !faculty.isEmpty()) {
            try {
                DatabaseHelper.addSubject(name, teacher, faculty);
                response.sendRedirect("subjects");
            } catch (SQLException e) {
                response.getWriter().println("Ошибка при добавлении предмета: " + e.getMessage());
            }
        }
    }

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
