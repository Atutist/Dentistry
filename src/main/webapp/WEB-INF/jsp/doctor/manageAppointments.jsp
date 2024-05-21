<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/style/font-awesome/css/all.css">
    <link href="/style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/style/style.css">
    <title>Confirm Appointment</title>
    <style>
        .table{
            width: 100%;
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
                <li><a href="/doctor/home">Home</a></li>
                <li><a href="/doctor/patients">View patients</a></li>
                <li><a href="/doctor/manageAppointments">See requested appointments</a></li>
            </ul>
        </div>
        <div class="left">Patients line</div>
        <div class="home">
            <a href="/doctor/home"><i class="fa-regular fa-user"></i></a>
        </div>
        <div class="logout">
            <form action="/logout" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>
        <div class="clear"></div>
    </header>
    <div class="authorization-container center">
        <h2>Appointment Requests</h2>
        <c:if test="${not empty appointments}">
            <table class="table center">
                <thead>
                <tr>
                    <th>Patient</th>
                    <th>Date and Time</th>
                    <th>Comments</th>
                    <th></th>
                    <th>Comment for patient</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.patientName}</td>
                        <td>${appointment.date} at ${appointment.time}</td>
                        <td>${appointment.comments}</td>
                        <td>
                            <form action="/appointments/confirmAppointment/${appointment.id}" method="post">
                                <td>
                                    <input type="text" name="comments" placeholder="Add comments here" class="note-appointment">
                                </td>
                                <td >
                                    <button type="submit" class="btn btn-success confirm-appointment">Confirm</button>
                                </td>

                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty appointments}">
            <p>No appointments to confirm.</p>
        </c:if>
    </div>
</div>
</body>
</html>
