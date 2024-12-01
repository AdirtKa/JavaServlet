<%@ page import="models.Subject" %>
<%@ page import="java.util.List" %>
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
            width: 70%;
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
            width: 40%;
            margin-top: 20px;
        }
        form input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ffb6c1;
            border-radius: 5px;
        }
        form input[type="submit"] {
            background-color: #d36b6b;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        form input[type="submit"]:hover {
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
    <tr><th>ID</th><th>Название</th><th>Преподаватель</th><th>Факультет</th></tr>
    <%
        // Извлекаем список предметов из атрибута запроса
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");

        // Проверка на null
        if (subjects != null && !subjects.isEmpty()) {
            for (Subject subject : subjects) {
    %>
    <tr>
        <td><%= subject.getId() %></td>
        <td><%= subject.getName() %></td>
        <td><%= subject.getTeacher() %></td>
        <td><%= subject.getFaculty() %></td>
        <td class="delete-column">
            <button class="delete-button" onclick="deleteSubject(<%= subject.getId() %>)">Удалить</button>
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
<form action="subjects" method="POST">
    <label for="name">Название:</label>
    <input type="text" id="name" maxlength="100" required name="name"><br>

    <label for="teacher">Преподаватель:</label>
    <input type="text" id="teacher" maxlength="100" required name="teacher"><br>

    <label for="faculty">Факультет:</label>
    <input type="text" id="faculty" maxlength="100" required name="faculty"><br>

    <input type="submit" value="Добавить">
</form>
<button class="redirect-button" onclick="window.location.href='index.jsp';">На главную</button>
<script>
    function deleteSubject(id) {
        // Подтверждение перед удалением
        if (confirm("Вы уверены, что хотите удалить этот предмет?")) {
            // Отправляем DELETE-запрос на сервер
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "subjects?id=" + id, true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    // Обновление страницы после успешного удаления
                    alert("Предмет удален!");
                    location.reload();  // Перезагружаем страницу для обновления списка
                } else {
                    alert("Ошибка при удалении предмета: " + xhr.status);
                }
            };
            xhr.send();
        }
    }
</script>

</body>
</html>
