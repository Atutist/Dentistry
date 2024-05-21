
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Home</title>
    <script>
        function toggleAppointments() {
            let list = document.getElementById('appointments-list');
            if (list.style.display === 'none' || list.style.display ==="") {
                list.style.display = 'block';
            }else{
                list.style.display ="none";
            }

        }

        function toggleRequest(){
            let list = document.getElementById('requested-list');
            if (list.style.display === 'none' || list.style.display ==="") {
                list.style.display = 'block';
            }else{
                list.style.display ="none";
            }
        }
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
                <li><a href="/patient/home">Home</a></li>
                <li><a href="/patient/bookAppointment">Book appointment</a></li>
                <li><a href="/patient/appointments">See appointments</a></li>
                <li><a href="/patient/personalAccount">Personal account</a></li>
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
        <section >
            <div class="authorization-container">
                <form action="/updatePersonalInfo" method="post">
                    <div class="form-group">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" id="name" name="name" class="form-control" value="${user.name}">
                    </div>
                    <div class="form-group">
                        <label for="surname" class="form-label">Surname:</label>
                        <input type="text" id="surname" name="surname" class="form-control" value="${user.surname}">
                    </div>
                    <div class="form-group">
                        <label for="phone" class="form-label">Phone number:</label>
                        <input type="text" id="phone" name="phone" class="form-control" value="${user.phone}">
                    </div>
                    <button type="submit" class="edit-button" style="margin-top:20px; width: 40%;height: 40px; border: none; background-color:  rgba(254, 203, 0, 1);color: rgba(0, 82, 147, 1)">Edit</button>
                </form>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                            ${successMessage}
                    </div>
                </c:if>
            </div>
        </section>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
