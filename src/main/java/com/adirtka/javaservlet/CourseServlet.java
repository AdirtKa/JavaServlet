package com.adirtka.javaservlet;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Course;
import models.SubjectName;

@WebServlet("/timetable")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SubjectName> subjects = new ArrayList<SubjectName>();
        List<Course> courses = new ArrayList<Course>();
        try {
            subjects = DatabaseHelper.getSubjectNames();
            courses = DatabaseHelper.getCourses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("courses", courses);
        request.setAttribute("subjects", subjects);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/schedule.jsp");
        rd.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dayOfWeek = request.getParameter("dayOfWeek");
        String time = request.getParameter("time");
        String classroom = request.getParameter("classroom");
        String subject_id = request.getParameter("subjectId");

        if (dayOfWeek != null && !dayOfWeek.isEmpty() && time != null && !time.isEmpty() && classroom != null && !classroom.isEmpty()) {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, parser);

            try {
                DatabaseHelper.addCourse(dayOfWeek, Time.valueOf(localTime), classroom, Integer.parseInt(subject_id));
                response.sendRedirect("timetable");
            } catch (SQLException e) {
                response.getWriter().println("Ошибка при добавлении урока: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                DatabaseHelper.deleteCourse(Integer.parseInt(id));
                response.sendRedirect("timetable");
            } catch (SQLException e) {
                response.getWriter().println("Ошибка при удалении урока: " + e.getMessage());
            }
        }
    }



}
