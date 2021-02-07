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
<h1 class="text-center">
    User management.
</h1>
<br/>

<p align="center"><a href="/userss" class="btn btn-primary">Add User</a></p>

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

    <div class="d-flex justify-content-between">

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Sort by:
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">


    <a class="dropdown-item"  href="/userss?action=listUser">Original order</a>
    <a class="dropdown-item"  href="/userss?action=listUser&sort=last_name_asc">Order by last name ascending</a>
    <a class="dropdown-item"  href="/userss?action=listUser&sort=last_name_desc">Order by last name descending</a>
    <a class="dropdown-item"  href="/userss?action=listUser&sort=birth_date_asc">Order by birth date ascending</a>
    <a class="dropdown-item"  href="/userss?action=listUser&sort=birth_date_desc">Order by birth date descending</a>


        </div>
    </div>


    <div class="w-50 d-flex">


    <form method="get" action="/userss" class="form-inline my-2 my-lg-0 mr-4 pl-3">

        <select name="sort" class="custom-select-sm drop btn btn-success" style="height: 38px;">
            <option value="">None</option>
            <option value="last_name_asc">last name asc</option>
            <option value="last_name_desc">last name desc</option>
            <option value="birth_date_asc">birth date asc</option>
            <option value="birth_date_desc">birth date desc</option>
        </select>

        <input maxlength="50" class="form-control mr-sm-2 w-50" type="text" name="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="search-button">Search</button>
    </form>

    </div>

    </div>

    <%
        int i = 1;
        List<UserViewModel> list = (List) request.getAttribute("users");
    %>

    <%
        if (!list.isEmpty()) {
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
            <a href="/userss?action=edit&userId=<%=u.getId()%>" class="btn btn-outline-success">Update</a>
            <a href="/userss?action=delete&userId=<%=u.getId()%>" class="btn btn-danger">Delete</a>
        </td>
    </tr>
    <%
        }
        }
    %>
    </tbody>
</table>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js" integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>