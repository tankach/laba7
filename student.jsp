<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student</title>
    <style>
        body {
            background-color: grey;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
        }
        form {
            width: 100%;
            max-width: 400px;
            margin: 20px auto;
        }
        table {
            width: 100%;
            margin-top: 10px;
            border-collapse: collapse;
        }
        table,
        th,
        td {
            border: 1px solid black;
        }
        th,
        td {
            padding: 10px;
            text-align: left;
        }
        input[type=text],
        input[type=email] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            margin-top: 5px;
            margin-bottom: 10px;
        }
        input[type=submit] {
            width: 100%;
            background-color: #4caf50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #45a049;
        }
        #content{
                background-color: white;
                width: 400px;
                margin: 20px auto;
            }
       </style>
</head>
<h1><%= (new java.util.Date()).toLocaleString()%></h1>
        <form method="post" action="StudentAdd">
            <table>
                <tbody>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td><input id="name" type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td><label for="surname">Surname</label></td>
                        <td><input id="surname" type="text" name="surname"></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input id="email" type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td><label for="group">Group</label></td>
                        <td><input id="group" type="text" name="group"></td>
                    </tr>
                    <tr>
                        <td><label for="faculty">Faculty</label></td>
                        <td><input id="faculty" type="text" name="faculty"></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="send" value="Відправити">
        </form>
        <div id="content">
        <c:if test="${students.size() > 0}">
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Group</th>
                    <th>Faculty</th>
                    <th>Scores</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><c:out value="${student.getId()}"/></td>
                        <td><c:out value="${student.getName()}"/></td>
                        <td><c:out value="${student.getSurname()}"/></td>
                        <td><c:out value="${student.getEmail()}"/></td>
                        <td><c:out value="${student.getGroup()}"/></td>
                        <td><c:out value="${student.getFaculty()}"/></td>
                            <td>
                                <a href="/UserContent?id2=${student.getId()}">scores</a>
                            </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>      
</html>
