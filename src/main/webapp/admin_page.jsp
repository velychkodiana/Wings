<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wings.model.User" %>
<%@ page import="com.wings.model.Flight" %>
<%@ page import="java.util.List" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getPosition())) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<String> overlays = (List<String>) request.getAttribute("overlayList");
    String message = (String) request.getAttribute("overlayMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>WingsAirlineAdminPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:wght@100;300;400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@400&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Comfortaa', sans-serif;
            background-color: #ffffff;
            color: #000;
            overflow-x: hidden;
        }
        .background {
            position: absolute;
            top: -55px;
            left: 50%;
            transform: translateX(-50%);
            width: 100%;
            min-width: 1585px;
            height: 1074px;
            object-fit: cover;
            z-index: -1;
        }
        .header-container {
            position: relative;
            width: 100%;
            height: 148px;
            overflow: hidden;
        }
        .wings-airline {
            position: absolute;
            top: 28px;
            left: 70px;
            font-family: 'Montserrat Alternates', sans-serif;
            font-size: 36px;
            display: flex;
            align-items: center;
            color: black;
        }
        .wing-icon {
            width: 45px;
            height: 45px;
            object-fit: cover;
            margin-right: 10px;
        }
        .button-container {
            position: absolute;
            top: 28px;
            right: 70px;
            display: flex;
            gap: 20px;
        }
        .button {
            border-radius: 45px;
            background-color: #ffffff;
            width: 153px;
            height: 35px;
            text-align: center;
            line-height: 35px;
            cursor: pointer;
            font-family: Comfortaa;
            font-size: 20px;
            transition: all 0.3s ease;
        }
        .main-container {
            max-width: 1400px;
            margin: 0 auto;
            padding: 20px;
        }
        .table-header {
            background-color: rgba(122, 141, 160, 0.5);
            height: 80px;
            display: flex;
            align-items: center;
            padding: 0 20px;
            margin-bottom: 10px;
            border-radius: 45px;
        }
        .header-item, .table-cell {
            background-color: #fff;
            border-radius: 45px;
            padding: 10px 15px;
            font-size: 16px;
            text-align: center;
            flex: 1;
            margin: 0 10px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .table-row {
            display: flex;
            background: rgba(197, 218, 239, 0.5);
            height: 60px;
            border-radius: 30px;
            margin: 10px 0;
            align-items: center;
            padding: 0 20px;
        }
        .action-buttons {
            display: flex;
            justify-content: center;
            margin-top: 40px;
            gap: 30px;
        }
        .action-button {
            font-size: 20px;
            color: #fff;
            background: #9fb3c7;
            border-radius: 45px;
            padding: 12px 30px;
            width: 350px;
            text-align: center;
            cursor: pointer;
            border: none;
            font-family: 'Comfortaa', sans-serif;
        }
        .overlay-msg {
            margin-top: 20px;
            font-weight: bold;
            text-align: center;
            font-size: 18px;
        }
        .overlay-list {
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="header-container">
    <img class="background" alt="" src="assets/images/background.jpg">
    <div class="wings-airline">
        <img class="wing-icon" src="assets/icons/wing.png" alt="wing icon">
        Wings Airline
    </div>
    <div class="button-container">
        <div class="button" onclick="navigateTo('schedule')">Schedule</div>
        <div class="button" onclick="navigateTo('logout')">Log out</div>
    </div>
</div>

<div class="main-container">
    <div class="table-header">
        <div class="header-item">Flight</div>
        <div class="header-item">Departure</div>
        <div class="header-item">Pilot 1</div>
        <div class="header-item">Pilot 2</div>
        <div class="header-item">Attendant</div>
        <div class="header-item">Wayfinder</div>
        <div class="header-item">Radio Operator</div>
        <div class="header-item">Gate</div>
        <div class="header-item">Status</div>
    </div>
    <%
        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
        if (flights != null) {
            for (Flight f : flights) {
    %>
    <div class="table-row">
        <div class="table-cell"><%= f.getFlightNumber() %></div>
        <div class="table-cell"><%= f.getDeparture() %></div>
        <div class="table-cell"><%= f.getPilot1Name() %></div>
        <div class="table-cell"><%= f.getPilot2Name() %></div>
        <div class="table-cell"><%= f.getAttendant1Name() %></div>
        <div class="table-cell"><%= f.getWayfinderName() %></div>
        <div class="table-cell"><%= f.getRadioOperatorName() %></div>
        <div class="table-cell"><%= f.getGate() %></div>
        <div class="table-cell"><%= f.getStatus() %></div>
    </div>
    <%
            }
        }
    %>
</div>

<div class="action-buttons">
    <form action="check-overlays" method="post">
        <button class="action-button" type="submit">Check for overlays</button>
    </form>
</div>

<% if (message != null) { %>
<div class="overlay-msg"><%= message %></div>
<% } %>

<% if (overlays != null && !overlays.isEmpty()) { %>
<div class="overlay-list">
    <ul>
        <% for (String o : overlays) { %>
        <li><%= o %></li>
        <% } %>
    </ul>
</div>
<% } %>

<script>
    function navigateTo(page) {
        window.location.href = page;
    }
</script>
</body>
</html>