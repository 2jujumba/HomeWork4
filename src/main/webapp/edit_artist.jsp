<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 09.12.2022
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Artist</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <jsp:useBean id="employee" scope="request" type="dev.jujumba.homework4.data.Employee"/>
    <form action="update_employee" method="post">
        <label>
            <input type="text" name="name" value="${employee.name}">
        </label>
        <input type="hidden" name="id" value="${employee.id}">
        <input type="submit" value="Update">
    </form>
</body>
</html>
