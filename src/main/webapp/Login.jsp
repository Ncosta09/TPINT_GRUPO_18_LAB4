<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema Bancario - Login</title>
    <link rel="stylesheet" href="CSS/login.css" type="text/css" />
</head>
<body class="login-body">
    <div class="login-container">
        <form class="login-form" action="ServletUsuario" method="post">
            <h2>Login</h2>
            
            <!-- mensaje de error-->
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
                <div class="error-alert">
                    <span class="error-icon">⚠️</span>
                    <%= error %>
                </div>
            <% } %>
            
            <div class="input-group">
                <input type="text" placeholder="Usuario" name="nombreUsuario" required />
            </div>
            <div class="input-group">
                <input type="password" placeholder="Contraseña" name="clave" required />
            </div>
            <div class="options">
                <label><input type="checkbox" />Recordarme</label>
            </div>
            <button type="submit" class="login-btn">Login</button>
            <div class="links">
                <a href="#">Recuperar contraseña</a>
                <a href="#">Registrarse</a>
            </div>
        </form>
    </div>

    <script src="js/login.js"></script>
</body>
</html>