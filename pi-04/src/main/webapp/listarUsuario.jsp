<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Users</title>
</head>
<body>
    <h2>Users from Database</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>CPF</th>
                <th>Status</th>
                <th>Grupo de usuario</th>
                <th>Alterar</th>
                <th>Deletar</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>${user.id_user}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.cpf}</td>
                    <td>${user.status}</td>
                    <td>${user.group_user}</td>
                    <td><a href="EditUserServlet?id=${user.id_user}">alterar usuario</td>
                    <td><button>Deletar usuario</button></td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
