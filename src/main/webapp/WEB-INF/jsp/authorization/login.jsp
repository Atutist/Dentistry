
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Login</title>
</head>
<body>
<div class="container">
    <header>
        <img src="../images/image 4.png" alt="" class="logo">
        <div class="left">Ideal Dentistry</div>
        <div class="clear"></div>
    </header>
    <main>
        <section class="login-form">
            <h1></h1>
            <div class="authorization-container">
                <form action="/login" method="post" class="input-form">
                    <input type="text" name="username" placeholder="Username" required><br>
                    <input type="password" name="password" placeholder=" Password" required><br>
                    <button type="submit">Sign in</button>
                    <c:if test="${message != null}">
                        <div class="failMessage"><c:out value="${message} "/></div>
                    </c:if>
                </form>
                <a href="/register" style="text-decoration: none;  color: rgba(0, 82, 147, 1); font-weight: 800"><button style="width: 80%;height: 40px; border: none; background-color:  rgba(254, 203, 0, 1);color: rgba(0, 82, 147, 1)">
                    Sign up</button></a>
            </div>
        </section>

    </main>
    <footer>

    </footer>
</div>
</body>
</html>
