package com.adirtka.javaservlet;

import models.Course;
import models.Subject;
import models.SubjectName;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;

public class DatabaseHelper {
    private static Connection connection = null;

    static {
        try {
            // Явно загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Метод для загрузки параметров подключения из properties файла в папке resources
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        // Используем ClassLoader для загрузки ресурса
        try (InputStream inputStream = DatabaseHelper.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                throw new IOException("Не удалось найти файл db.properties");
            }
            properties.load(inputStream);
        }

        return properties;
    }

    // Метод для получения соединения с базой данных
    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            Properties properties = loadProperties();
            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
        return connection;
    }

    // Метод для закрытия соединения с базой данных
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения всех записей из таблицы "subjects"
    public static List<Subject> getSubjects() throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "SELECT * FROM subjects";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Subject> subjects = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String teacher = rs.getString("teacher");
            String faculty = rs.getString("faculty");

            Subject subject = new Subject(id, name, teacher, faculty);
            subjects.add(subject);
        }

        rs.close();
        stmt.close();
        conn.close();

        return subjects;
    }

    public static List<Course> getCourses() throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "SELECT schedule.id, schedule.day_of_week, to_char(schedule.time, 'hh24:mi') as time, s.name, schedule.classroom\n" +
                "FROM schedule\n" +
                "INNER JOIN subjects s on s.id = schedule.subject_id;";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String dayOfWeek = rs.getString("day_of_week");
            String time = rs.getString("time");
            String name = rs.getString("name");
            String classroom = rs.getString("classroom");
            Course course = new Course(id, dayOfWeek, time, name, classroom);
            courses.add(course);
        }
        rs.close();
        stmt.close();
        conn.close();
        return courses;
    }

    public static List<SubjectName> getSubjectNames() throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "SELECT subjects.id, subjects.name\n" +
                "FROM subjects;";
        ResultSet rs = conn.createStatement().executeQuery(query);
        List<SubjectName> subjectNames = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            subjectNames.add(new SubjectName(id, name));
        }
        rs.close();
        conn.close();
        return subjectNames;
    }

    // Метод для добавления записи в таблицу "subjects"
    public static void addSubject(String name, String teacher, String faculty) throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "INSERT INTO subjects (name, teacher, faculty) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, teacher);
        ps.setString(3, faculty);
        ps.executeUpdate();
    }

    public static void addCourse(String day_of_week, Time time, String classroom, int subject_id)
    throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "INSERT INTO schedule (day_of_week, time, classroom, subject_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, day_of_week);
        ps.setTime(2, time);
        ps.setString(3, classroom);
        ps.setInt(4, subject_id);
        ps.executeUpdate();
    }

    // Метод для удаления записи из таблицы "subjects"
    public static void deleteSubject(int id) throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "DELETE FROM subjects WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void deleteCourse(int id) throws SQLException, IOException {
        Connection conn = getConnection();
        String query = "DELETE FROM schedule WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, IOException {
        for (Subject subject : DatabaseHelper.getSubjects()){
            System.out.println(subject.getName());
        }
    }

}
