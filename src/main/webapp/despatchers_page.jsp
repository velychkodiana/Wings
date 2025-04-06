<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wings.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"dispatcher".equals(user.getPosition())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>WingsAirlineDispatcherPage</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:wght@100;300;400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@400&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <style>
        * { box-sizing: border-box; }
        body {
            font-family: 'Comfortaa', sans-serif;
            margin: 0;
            background: #f5f7fa;
        }
        .header {
            background-color: #fff;
            padding: 20px 70px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .wings-airline {
            font-family: 'Montserrat Alternates', sans-serif;
            font-size: 32px;
            display: flex;
            align-items: center;
            color: #000;
        }
        .wings-airline img {
            width: 45px;
            height: 45px;
            object-fit: cover;
            margin-right: 10px;
        }
        .button {
            background-color: #fff;
            border: 2px solid #ccc;
            border-radius: 45px;
            padding: 8px 24px;
            font-size: 16px;
            font-family: 'Comfortaa', sans-serif;
            cursor: pointer;
        }
        .form-title {
            text-align: center;
            font-size: 28px;
            font-weight: bold;
            margin-top: 40px;
            margin-bottom: 20px;
            color: #2c3e50;
            font-family: 'Montserrat Alternates', sans-serif;
        }
        .form-container {
            max-width: 1200px;
            margin: auto;
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            padding: 20px;
        }
        .form-section {
            background: #fff;
            border-radius: 20px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-section h3 {
            font-size: 18px;
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            font-family: 'Montserrat Alternates', sans-serif;
        }
        .form-section h3 i {
            margin-right: 10px;
        }
        .form-section label {
            display: block;
            font-size: 14px;
            margin-bottom: 5px;
            margin-top: 15px;
        }
        .form-section input {
            width: 100%;
            padding: 10px 15px;
            border-radius: 15px;
            border: 1px solid #ccc;
            font-family: 'Comfortaa', sans-serif;
            font-size: 14px;
        }
        .submit-container {
            text-align: center;
            margin: 40px 0;
        }
        .submit-btn {
            background-color: #9fb3c7;
            color: #fff;
            font-size: 20px;
            padding: 12px 40px;
            border: none;
            border-radius: 45px;
            cursor: pointer;
            font-family: 'Comfortaa', sans-serif;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="wings-airline">
        <img src="assets/icons/wing.png" alt="wing icon">
        Wings Airline
    </div>
    <div class="button" onclick="location.href='logout'">Log out</div>
</div>

<div class="form-title">✈️ Create New Flight</div>

<form action="create-flight" method="post">
    <div class="form-container">
        <!-- Flight Details -->
        <div class="form-section">
            <h3><i class="fas fa-plane"></i> Flight Details</h3>
            <label>Flight Number</label>
            <input type="text" name="flightNumber" required>

            <label>Departure Time</label>
            <input type="datetime-local" name="departure" required>

            <label>Gate</label>
            <input type="text" name="gate" required>

            <label>Status</label>
            <input type="text" name="status" placeholder="e.g. scheduled" required>

            <label>Destination</label>
            <input type="text" name="destination" required>
        </div>

        <!-- Pilots -->
        <div class="form-section">
            <h3><i class="fas fa-user-tie"></i> Pilots</h3>
            <label>Pilot 1 Name</label>
            <input type="text" name="pilot1" required>

            <label>Pilot 2 Name</label>
            <input type="text" name="pilot2" required>
        </div>

        <!-- Cabin Crew -->
        <div class="form-section">
            <h3><i class="fas fa-people-group"></i> Cabin Crew</h3>
            <label>Attendant 1</label>
            <input type="text" name="attendant1" required>

            <label>Attendant 2</label>
            <input type="text" name="attendant2" required>
        </div>

        <!-- Specialists -->
        <div class="form-section">
            <h3><i class="fas fa-headset"></i> Specialists</h3>
            <label>Radio Operator</label>
            <input type="text" name="radio" required>

            <label>Wayfinder</label>
            <input type="text" name="wayfinder" required>
        </div>
    </div>

    <div class="submit-container">
        <button type="submit" class="submit-btn">Create Flight</button>
    </div>
</form>
<%
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<div style="text-align:center; margin-top: 10px;">
    <% if (successMessage != null) { %>
    <div style="color: green; font-weight: bold; font-size: 18px;"><%= successMessage %></div>
    <% } else if (errorMessage != null) { %>
    <div style="color: red; font-weight: bold; font-size: 18px;"><%= errorMessage %></div>
    <% } %>
</div>
</body>
</html>