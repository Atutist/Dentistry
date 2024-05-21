
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Manage</title>
    <script>
    </script>
</head>
<body>
<div class="container">
    <header>
        <div class="logo"></div>
        <div class="burger-menu" id="menuToggle">
            <input type="checkbox" />
            <span></span>
            <span></span>
            <span></span>
            <ul id="menu">
                <li><a href="/admin/registerNewUser">Create new User</a></li>
                <li><a href="/admin/home">Home</a></li>
            </ul>
        </div>
        <div class="left">Ideal Dentistry</div>
        <div class="home">
            <a href="../doctor/home"><i class="fa-regular fa-user"></i></a>
        </div>
        <div class="logout">
            <form action="/logout" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>
        <div class="clear"></div>
    </header>
    <main>
        <section class="profile">
            <div class="row">
                <div class="col-lg-7 center">
                    <h1>Hello admin, <br> Welcome back!</h1>
                </div>
                <div class="col-lg-5 center">
                    <div class="circle"><h1>User</h1></div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="center patient-box">
                <a href="/admin/registerNewUser"><button class="view-patients">Create new User</button></a>
                <a href="/admin/appointments" ><button class="view-patients" style="margin-top: 20px">Change status of appointment</button></a>
            </div>
        </section>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
