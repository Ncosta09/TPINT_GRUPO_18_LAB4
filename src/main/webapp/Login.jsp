<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="CSS/login.css" type="text/css" />
</head>
<body class="login-body">
	<div class="login-container">
        <form class="login-form">
            <h2>Login</h2>

            <div class="input-group">
                <input type="email" placeholder="Email" required />
            </div>

            <div class="input-group">
                <input type="password" placeholder="Contraseña" required />
            </div>

            <div class="options">
                <label><input type="checkbox" />Recordarme</label>
            </div>

            <button type="submit" class="login-btn">Login</button>

            <div class="links">
                <a href="#">Recuperar contraseña</a>
                <a href="#">Regsitrarse</a>
            </div>
        </form>
    </div>
</body>
</html>