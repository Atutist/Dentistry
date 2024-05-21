<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Confirm Appointment</title>
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
                <li><a href="../doctor/patients.html">View patients</a></li>
                <li><a href="../doctor/manageAppointments.html">See requested appointments</a></li>
            </ul>
        </div>
        <div class="left">Ideal Dentistry</div>
        <div class="home">
            <a href="../doctor/home.html"><i class="fa-regular fa-user"></i></a>
        </div>
        <div class="clear"></div>
    </header>
    <div class="authorization-container center">
        <h2>Appointment Requests</h2>
        <c:if test="${not empty appointments}">
            <table class="appointments-table">
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td><h3>${appointment.patient.name}</h3></td>
                        <td> <h3>${appointment.patient.surname}</h3></td>
                    </tr>
                    <tr>
                        <td colspan="2"><h3>${appointment.dateTime}</h3></td>
                        <!-- <td>${appointment.comments}</td> -->
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="text" name="note-appointment" placeholder="Please leave some comments for an appointment"
                                   class="note-appointment">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form action="/appointments/confirm/${appointment.id}" method="post">
                                <button type="submit" class="confirm-appointment">Confirm</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:if>
        <c:if test="${empty appointments}">
            <p>No appointments to confirm.</p>
        </c:if>
    </div>
</div>

</body>
</html>

