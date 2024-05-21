<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Registration</title>
    <style>
        .login-sign:hover{
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <img src="../images/image 4.png" alt="" class="logo">
        <div class="left">Ideal Dentistry</div>
        <div class="left"><a href="/" style="text-decoration: none; color: white;">Login</a></div>
        <div class="clear"></div>
    </header>
    <section class="login-form">
        <div class="registration-container">
            <h1>Patient registration</h1>
            <form action="/register" method="post" class="input-form">
                <input type="text" name="name" placeholder="Ім'я" required>
                <input type="text" name="surname" placeholder="Прізвище" required>
                <input type="text" name="username" placeholder="Username" required>
                <input type="text" name="phone" placeholder="Телефон" required>
                <input type="password" name="password" placeholder="Пароль" required>
                <button type="submit">Зареєструватися</button>
            </form>
        </div>
    </section>

</div>
</body>
</html>
