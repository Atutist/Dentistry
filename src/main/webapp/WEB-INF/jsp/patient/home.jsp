
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
        <section class="profile">
            <div class="row">
                <div class="col-lg-7 center">
                    <h1>Hello patient, <br> Welcome back!</h1>
                </div>
                <div class="col-lg-5 center">
                    <div class="circle"><h1>User</h1></div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="center patient-box">
                <a href="../patient/bookAppointment"><button class="view-patients" id="view-patients" >BOOK APPOINTMENT</button></a>
            </div>
            <h1>MY APPOINTMENTS</h1>
            <div class="doctor-schedule">

                <div class="row center">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4"><h4>CONFIRMED</h4></div>
                    <div class="col-lg-4"><button class="sort sort-appointments" onclick="toggleAppointments()"><i class="fa-solid fa-sort" ></i></button></div>
                </div>

            </div>
            <div class="appointments-list" id="appointments-list" style="display:none;">
                <ul>
                    <c:forEach items="${confirmedAppointments}" var="appointment">
                        <li>
                            <h5>${appointment.date} at ${appointment.time} - Confirmed</h5>
                            <c:if test="${not empty appointment.comments}">
                                <p> Comments:
                                <p style="color:rgba(0, 82, 147, 1)">${appointment.comments}</p>
                                </p>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <br>
            <h1>IN PROCESS</h1>
            <div class="doctor-schedule">

                <div class="row center">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-4"><h4>IN PROCESS</h4></div>
                    <div class="col-lg-4"><button class="sort sort-appointments" onclick="toggleRequest()"><i class="fa-solid fa-sort" ></i></button></div>
                </div>

            </div>
            <div class="appointments-list" id="requested-list" style="display:none;">
                <ul>
                    <c:forEach items="${requestedAppointments}" var="appointment">
                        <li>
                            <h5>${appointment.date} at ${appointment.time} - Requested</h5>
                            <c:if test="${not empty appointment.comments}" >
                                <p >
                                Comments:
                                    <p style="color:rgba(0, 82, 147, 1)">
                                        ${appointment.comments}
                                    </p>

                                </p>
                            </c:if>
                        </li>

                    </c:forEach>
                </ul>
            </div>
        </section>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
