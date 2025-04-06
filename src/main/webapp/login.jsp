<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>WingsAirlineLogin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, width=device-width">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:wght@100;300;400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@400&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <style>
        body {
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        .background {
            position: absolute;
            top: -55px;
            left: -128px;
            width: 1585px;
            height: 1074px;
            object-fit: cover;
        }
        .wings-airline {
            position: absolute;
            top: 28px;
            left: 70px;
            font-family: 'Montserrat Alternates', sans-serif;
            font-size: 36px;
            align-items: center;
            display: flex;
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
        }
        .button_account {
            position: absolute;
            border-radius: 50px;
            background-color: #ffffff;
            width: 150px;
            height: 45px;
            text-align: center;
            font-size: 14px;
            top: 480px;
            line-height: 45px;
            cursor: pointer;
            font-family: Comfortaa;
        }
        .login-box {
            position: absolute;
            top: 180px;
            left: 150px;
            width: 320px;
            height: 285px;
            background: rgba(122, 141, 160, 0.5);
            border-radius: 30px;
            text-align: center;
            font-size: 32px;
            padding-top: 36px;
        }
        .input-wrapper {
            position: absolute;
            width: 259px;
            height: 48px;
            left: 180px;
        }
        .input-field {
            width: 100%;
            height: 48px;
            border-radius: 50px;
            background-color: rgba(255, 255, 255, 0.5);
            text-align: left;
            line-height: 48px;
            font-size: 14px;
            border: none;
            outline: none;
            font-family: Comfortaa;
            padding-left: 15px;
        }
        .icon {
            position: absolute;
            top: 50%;
            right: 15px;
            transform: translateY(-50%);
            color: darkgray;
            font-size: 18px;
        }
        .login-container {
            position: relative;
            width: 100%;
            height: 832px;
            background-color: #ffffff;
            overflow: hidden;
            text-align: center;
            font-size: 20px;
            color: #000;
            font-family: Comfortaa;
        }
    </style>
</head>
<body>
<div class="login-container">
    <img class="background" alt="" src="assets/images/background.jpg">
    <div class="wings-airline">
        <img class="wing-icon" src="assets/icons/wing.png" alt="wing icon">
        Wings Airline
    </div>

    <div class="login-box">Log in</div>

    <form action="login" method="post">
        <div class="input-wrapper" style="top: 260px;">
            <i class="fa fa-user icon"></i>
            <input type="text" class="input-field" placeholder="Username" name="username" required>
        </div>

        <div class="input-wrapper" style="top: 320px;">
            <i class="fa fa-lock icon"></i>
            <input type="password" class="input-field" placeholder="Password" name="password" required>
        </div>

        <input class="button_account" style="top: 401px; left: 235px;" type="submit" value="Get into account">
    </form>

        <% if (request.getParameter("error") != null) { %>
    <p style="color: red; position: absolute; top: 460px; left: 220px;">Invalid credentials</p>
<% } %>
    <div class="button-container">
        <div class="button" onclick="navigateTo('sign_up.jsp')">Sign up</div>
        <div class="button" onclick="navigateTo('schedule')">Schedule</div>
        <div class="button" onclick="logout()">Log out</div>
    </div>
</div>

<script>
    function logout() {
        window.location.href = 'logout';
    }

    function navigateTo(page) {
        window.location.href = page;
    }
</script>
</body>
</html>