<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 09.12.2022
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Artists</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <jsp:useBean id="employeeBean" type="dev.jujumba.homework4.beans.EmployeeBean" scope="request"/>
    <form action="add_employee" method="post">
        <label for="employeeName">New artist:</label>
        <input type="text" name="employeeName" id="employeeName">
        <input type="submit" value="Add">
    </form>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Delete</th>
            <th>Edit</th>
            <th>Customers</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${employeeBean.artists}" var="a">
                <tr>
                    <td>${a.id}</td>
                    <td>${a.name}</td>
                    <td><a href="delete_employee?id=${a.id}">delete</a></td>
                    <td><a href="edit_employee?id=${a.id}">edit</a></td>
                    <td><a href="show_customers_of?id=${a.id}">show</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
