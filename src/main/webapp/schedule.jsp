<%@ page import="models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Course" %>
<%@ page import="models.SubjectName" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список предметов</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #ffe4e1; /* Светло-розовый фон */
            color: #5a3e3e; /* Темно-розовый текст */
        }
        h2 {
            color: #d36b6b; /* Насыщенно-розовый цвет заголовка */
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin-top: 20px;
        }
        td {
            border: 1px solid #ffb6c1; /* Светло-розовая рамка */
            background-color: #ffecec; /* Бледно-розовый фон таблицы */
        }
        th, td {
            text-align: left;
            padding: 10px;
        }
        th {
            background-color: #ffb6c1; /* Светло-розовый фон заголовков */
            color: #5a3e3e; /* Темный текст */
        }
        td button {
            background-color: #d36b6b; /* Насыщенно-розовый цвет кнопки */
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        td button:hover {
            background-color: #b64a4a; /* Темный розовый при наведении */
        }
        h3 {
            margin-top: 30px;
            color: #d36b6b;
        }
        form {
            background-color: transparent; /* Бледно-розовый фон формы */
            padding: 20px;
            border-radius: 8px;
            width: 50%;
            margin-top: 20px;
        }
        form input[type="text"], form input[type="time"], form select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ffb6c1;
            border-radius: 5px;
        }
        form button {
            background-color: #d36b6b;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        form button:hover {
            background-color: #b64a4a; /* Темный розовый при наведении */
        }
        .delete-column {
            border: none;
            background-color: transparent;
        }
        /* Стили для кнопки внутри ячейки */
        .delete-button {
            background-color: #d36b6b;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            position: relative; /* Разрешаем позиционирование кнопки внутри ячейки */
            z-index: 10; /* Убедитесь, что кнопка находится поверх других элементов */
        }
        .delete-button:hover {
            background-color: #b64a4a; /* Темный розовый при наведении */
        }

        .redirect-button {
            background-color: #d36b6b; /* Насыщенно-розовый цвет кнопки */
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        .redirect-button:hover {
            background-color: #b64a4a; /* Темный розовый при наведении */
        }
    </style>


</head>
<body>
<h2>Список предметов</h2>
<table>
    <tr><th>ID</th><th>День недели</th><th>Время</th><th>Название</th><th>Аудитория</th></tr>
    <%
        // Извлекаем список предметов из атрибута запроса
        List<Course> courses = (List<Course>) request.getAttribute("courses");

        // Проверка на null
        if (courses != null && !courses.isEmpty()) {
            for (Course course : courses) {
    %>
    <tr>
        <td><%= course.getId() %></td>
        <td><%= course.getDayOfWeek() %></td>
        <td><%= course.getTime() %></td>
        <td><%= course.getName() %></td>
        <td><%= course.getClassroom()%></td>
        <td class="delete-column">
            <button class="delete-button" onclick="deleteLesson(<%= course.getId() %>)">Удалить</button>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="4">Нет доступных данных</td></tr>
    <%
        }
    %>
</table>
<h3>Добавить предмет</h3>
<form action="timetable" method="POST">
    <label for="dayOfWeek">День недели:</label>
    <select name="dayOfWeek" id="dayOfWeek" required>
        <option value="">Выберите</option>
        <option value="Понедельник">Понедельник</option>
        <option value="Вторник">Вторник</option>
        <option value="Среда">Среда</option>
        <option value="Четверг">Четверг</option>
        <option value="Пятница">Пятница</option>
        <option value="Суббота">Суббота</option>
        <option value="Воскресенье">Воскресенье</option>
    </select><br>

    <label for="time">Время:</label>
    <input type="time" name="time" id="time" required><br>

    <label for="classroom">Аудитория:</label>
    <input type="text" name="classroom" id="classroom" required><br>
    <label for="subjectId">Предмет:</label>
    <select name="subjectId" id="subjectId" required>
        <option value="">Выберите предмет</option>
        <%
            List<SubjectName> subjects = (List<SubjectName>) request.getAttribute("subjects");
            if (subjects != null && !subjects.isEmpty()) {
                for (SubjectName subject : subjects) {
        %>
        <option value="<%= subject.getSubjectId() %>"><%= subject.getSubjectName() %></option>
        <%
            }
        } else {
        %>
        <option value="">Предметы отсутствуют</option>
        <% } %>
    </select><br>

    <button type="submit">Добавить</button>
</form>
<button class="redirect-button" onclick="window.location.href='index.jsp';">На главную</button>
<script>
    function deleteLesson(id) {
        // Подтверждение перед удалением
        if (confirm("Вы уверены, что хотите удалить этот предмет?")) {
            // Отправляем DELETE-запрос на сервер
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "timetable?id=" + id, true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    // Обновление страницы после успешного удаления
                    alert("Урок удален!");
                    location.reload();  // Перезагружаем страницу для обновления списка
                } else {
                    alert("Ошибка при удалении урока: " + xhr.status);
                }
            };
            xhr.send();
        }
    }
</script>
</body>
</html>
