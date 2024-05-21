
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../style/font-awesome/css/all.css">
    <link href="../style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <title>Create new user</title>
    <script>
        function showRoleForm() {
            var role = document.getElementById('roleSelect').value;
            var forms = document.getElementsByClassName('role-form');
            for (var i = 0; i < forms.length; i++) {
                forms[i].style.display = 'none'; // Hide all forms
            }
            var formToShow = document.getElementById(role + 'Form');
            if (formToShow) {
                formToShow.style.display = 'block'; // Show the selected form
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
                <li><a href="/admin/home">Home</a></li>
                <li><a href="/admin/appointments">Change status of appointment</a></li>
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
        <section class="login-form ">
            <select id="roleSelect" onchange="showRoleForm()">
                <option value="">Select Role</option>
                <option value="Patient">Patient</option>
                <option value="Doctor">Doctor</option>
                <option value="Administrator">Administrator</option>
            </select>

            <!-- Patient Form -->
            <div id="PatientForm" class="role-form registration-container" style="display:none;">
                <form action="/admin/registerUser" method="post" class="input-form">
                    <input type="hidden" name="role" value="PATIENT">
                    <input type="text" placeholder="Name" name="name" required>
                    <input type="text" placeholder="Surname" name="surname" required>
                    <input type="text" placeholder="Username" name="username" required>
                    <input type="password" placeholder="Password" name="password" required>
                    <input type="text" placeholder="Phone Number" name="phone" required>
                    <button type="submit" class="btn btn-primary">Register Patient</button>
                </form>
            </div>

            <!-- Doctor Form -->
            <div id="DoctorForm" class="role-form registration-container" style="display:none;">
                <form action="/admin/registerUser" method="post" class="input-form">
                    <input type="hidden" name="role" value="DOCTOR">
                    <input type="text" placeholder="Name" name="name" required>
                    <input type="text" placeholder="Surname" name="surname" required>
                    <input type="text" placeholder="Username" name="username" required>
                    <input type="password" placeholder="Password" name="password" required>
                    <input type="text" placeholder="Specialization" name="specialization" required>
                    <button type="submit" class="btn btn-primary">Register Doctor</button>
                </form>
            </div>

            <!-- Administrator Form -->
            <div id="AdministratorForm" class="role-form registration-container" style="display:none;">
                <form action="/admin/registerUser" method="post" class="input-form">
                    <input type="hidden" name="role" value="ADMINISTRATOR">
                    <input type="text" placeholder="Name" name="name" required>
                    <input type="text" placeholder="Surname" name="surname" required>
                    <input type="text" placeholder="Username" name="username" required>
                    <input type="password" placeholder="Password" name="password" required>
                    <input type="text" placeholder="Phone Number" name="phone" required>
                    <button type="submit" class="btn btn-primary">Register Administrator</button>
                </form>
            </div>

        </section>

    </main>
    <footer>

    </footer>
</div>
</body>
</html>