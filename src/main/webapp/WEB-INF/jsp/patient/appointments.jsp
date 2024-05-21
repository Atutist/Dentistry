<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <title>Request Appointment</title>
    <script>

    </script>
    <style>
        .appointment-section{
            margin: 0 auto;
        }
        .appointment{
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            color: white;
        }
        .appointment-item{
            padding: 20px;
            width: 300px;
            background-color:  rgba(0, 82, 147, 1);;
            margin: 30px;
        }
    </style>
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
                <li><a href="../patient/home">Home</a></li>
                <li><a href="../patient/bookAppointment">Book appointment</a></li>
                <li><a href="../patient/appointments">See appointments</a></li>
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
    <div class="appointment-section">
        <h1 class="center">My appointments</h1>
        <div class="appointment">
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">
                        ${successMessage}
                </div>
            </c:if>
            <c:forEach items="${appointments}" var="appointment">
                <div class="appointment-item">
                    <p><i class="fa-regular fa-calendar"></i> ${appointment.date}</p>
                    <p><i class="fa-regular fa-clock"></i>   ${appointment.time}</p>
                    <p><i class="fa-regular fa-user"></i>   Dr. ${appointment.patientName}</p>
                    <p>Comments: ${appointment.comments}</p>
                    <form action="${pageContext.request.contextPath}/appointments/cancel/${appointment.id}" method="post">
                        <button type="submit" class="btn btn-danger">Cancel</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>



</div>
</body>
</html>
