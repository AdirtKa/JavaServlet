<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Приветствие и список таблиц</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #ffe4e1; /* Светло-розовый фон */
            color: #5a3e3e; /* Темно-розовый текст */
        }
        h1 {
            color: #d36b6b; /* Насыщенно-розовый цвет заголовка */
        }
        table {
            border-collapse: collapse;
            width: 50%;
            margin-top: 20px;
            background-color: #ffecec; /* Бледно-розовый фон таблицы */
        }
        table, th, td {
            border: 1px solid #ffb6c1; /* Светло-розовая рамка */
        }
        th, td {
            text-align: left;
            padding: 10px;
        }
        th {
            background-color: #ffb6c1; /* Светло-розовый фон заголовков */
            color: #5a3e3e; /* Темный текст */
        }
        a {
            text-decoration: none;
            color: #d36b6b; /* Насыщенно-розовый */
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Добро пожаловать!</h1>
<p>Выберите таблицу из списка ниже, чтобы увидеть её содержимое:</p>

<table>
    <tr>
        <th>Имя таблицы</th>
        <th>Ссылка</th>
    </tr>
    <tr>
        <td>Предметы</td>
        <td><a href="subjects">Открыть</a></td>
    </tr>
    <tr>
        <td>Расписание</td>
        <td><a href="timetable">Открыть</a></td>
    </tr>
</table>
</body>
</html>
