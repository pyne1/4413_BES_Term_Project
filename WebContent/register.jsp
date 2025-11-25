<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <!-- Link to the shared YorkU theme CSS -->
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container">

        <h1>Create Your Account</h1>

        <% 
            String error = (String) request.getAttribute("error"); 
            if (error != null) { 
        %>
            <p style="color:red"><%= error %></p>
        <% 
            } 
        %>

        <form action="<%= request.getContextPath() %>/register" method="post">

            <!-- FIRST NAME -->
            <label>First Name:</label><br>
            <input type="text" name="firstName" required><br><br>

            <!-- LAST NAME -->
            <label>Last Name:</label><br>
            <input type="text" name="lastName" required><br><br>

            <!-- EMAIL -->
            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>

            <!-- PASSWORD -->
            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>

            <!-- CREDIT CARD NUMBER -->
            <label>Credit Card Number:</label><br>
            <input type="text" name="creditCardNumber"><br><br>

            <!-- CREDIT CARD EXPIRY -->
            <label>Credit Card Expiry (MM/YY):</label><br>
            <input type="text" name="creditCardExpiry"><br><br>

            <!-- CREDIT CARD CVV -->
            <label>Credit Card CVV:</label><br>
            <input type="text" name="creditCardCVV"><br><br>

            <!-- ADDRESS ID -->
            <input type="hidden" name="addressId" value="1" />

            <button type="submit" class="btn">Create Account</button>
        </form>

        <div class="footer">
            Already have an account? <a href="login.jsp">Sign In</a>
        </div>

    </div>
</body>
</html>
