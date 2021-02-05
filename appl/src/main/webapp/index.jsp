<%@ page import="java.util.List" %>
<%@ page import="APP.Model.UserViewModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<h1><%= "Hello World!" %>
    Heloooooo

    <%= request.getAttribute("name") %>
</h1>
<br/>

<p align="center"><a href="users?action=insert">Add User</a></p>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First name.</th>
        <th scope="col">Last name.</th>
        <th scope="col">Birth date.</th>
        <th scope="col">Phone number.</th>
        <th scope="col">Email address.</th>
        <th scope="col">Options</th>
    </tr>
    </thead>

    <tbody>

    <%
        int i = 1;
        List<UserViewModel> list = (List) request.getAttribute("users");
    %>

    <%
        for (UserViewModel u : list) {
    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getFirstName()%></td>
        <td><%=u.getLastName()%></td>
        <td><%=u.getBirthDate()%></td>
        <td><%=u.getPhoneNumber()%></td>
        <td><%=u.getEmail()%></td>
        <td>
            <a href="users?action=edit&id=<%=u.getId()%>">Update</a>
            <a href="users?action=delete&id=<%=u.getId()%>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf" crossorigin="anonymous"></script>
</body>
</html>