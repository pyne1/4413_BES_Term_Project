<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
    <div class="container">
        <h1>Sign In</h1>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p style="color:red"><%= error %></p>
        <%
            }
        %>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <!-- EMAIL -->
            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>

            <!-- PASSWORD -->
            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>

            <button type="submit" class="btn">Sign In</button>
        </form>

        <div class="footer">
            Don’t have an account yet?
            <a href="register.jsp">Register</a>
        </div>
    </div>
</body>
</html>
