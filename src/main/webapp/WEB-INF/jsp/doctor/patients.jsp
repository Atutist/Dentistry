
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Patients</title>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const searchInput = document.getElementById('search-patient');
            const patientList = document.getElementById('patient-list').getElementsByTagName('li');

            searchInput.addEventListener('keyup', function() {
                const searchText = searchInput.value.toLowerCase();
                Array.from(patientList).forEach(function(item) {
                    const itemText = item.textContent.toLowerCase();
                    if (itemText.includes(searchText)) {
                        item.style.display = '';
                    } else {
                        item.style.display = 'none';
                    }
                });
            });
        });
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
                <li><a href="../doctor/home">Home</a></li>
                <li><a href="../doctor/patients">View patients</a></li>
                <li><a href="../doctor/manageAppointments">See requested appointments</a></li>
            </ul>
        </div>
        <div class="left">Check patients</div>
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
        <h1 class="center"> Check all your patients</h1>
        <section class="patient-list-wrapper center">
            <input type="text" placeholder="Search patient" id="search-patient">
            <ul id="patient-list" class="patient-list">
                <c:forEach items="${patients}" var="patient">
                    <li><h5>${patient.name} ${patient.surname}</h5></li>
                </c:forEach>
            </ul>
            <c:if test="${empty patients}">
                <p>No patients found.</p>
            </c:if>

        </section>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
