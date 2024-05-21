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
        $(function() {
            $("#datepicker").datepicker({
                minDate: 1, // Start from next day
                dateFormat: 'yy-mm-dd' // Format that matches the backend expectation
            });
        });
        $(function() {
            $("#datepicker").datepicker({
                onSelect: function(dateText) {
                    $.ajax({
                        url: '/appointments/available-times',
                        type: 'GET',
                        data: {date: dateText},
                        success: function(times) {
                            var timeSelect = $('#time');
                            timeSelect.empty();
                            $.each(times, function(i, time) {
                                timeSelect.append($('<option></option>').attr('value', time).text(time));
                            });
                        }
                    });
                }
            });
        });

    </script>
    <style>
        .calendar{
            width: 60%;
            margin: 20px;
        }
        .timepicker{
            margin: 20px;
            width: 30%;
        }
        .select-doctor{
            border: solid 2px gray;
            padding: 0 0 0 20px;
            text-align: center;
        }
        .select-doctor:hover{
            border: solid 2px black;
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
    <section class="book-section">
        <form action="/appointments/requestAppointment" method="post" class="input-form">
            <input type="hidden" name="patientId" value="${patientId}">
            <h3><%--@declare id="doctorid"--%><label for="doctorId">Select Doctor:</label></h3>
            <select name="doctorId" class="select-doctor" style="width: 60%; margin-left: 20px" required>
                <c:forEach items="${doctors}" var="doctor">
                    <option value="${doctor.id}">${doctor.name} - ${doctor.specialization}</option>
                </c:forEach>
            </select>
            <h3><label for="datepicker">Select date:</label></h3>
            <input type="text" id="datepicker" name="date" style="width: 60%; margin-left: 20px" required>
            <h3><%--@declare id="time"--%><label for="time">Select time:</label></h3>
            <select name="time" class="center timepicker" required>
                <option value="09:00">9:00 AM - 9:40 AM</option>
                <option value="09:40">9:40 AM - 10:20 AM</option>
                <option value="10:20">10:20 AM - 11:00 AM</option>
                <option value="11:00">11:00 AM - 11:40 AM</option>
                <option value="11:40">11:40 AM - 12:20 AM</option>
                <option value="12:20">12:20 AM - 1:00 PM</option>
                <option value="1:00">1:00 PM - 1:40 PM</option>
                <option value="1:40">1:40 PM - 2:20 PM</option>
                <option value="2:20">2:20 PM - 3:00 PM</option>
            </select>
            <h3><%--@declare id="comments"--%><label for="comments">Comments:</label></h3>

            <textarea name="comments" placeholder="Comments" style="width: 60%; height: 100px; margin: 20px"></textarea>            <button type="submit">Request Appointment</button>
        </form>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                    ${successMessage}
            </div>
        </c:if>
    </section>

</div>
</body>
</html>
