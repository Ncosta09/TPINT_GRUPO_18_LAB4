<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
	<link rel="stylesheet" href="CSS/common.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="welcome-card">
                <h2>Bienvenido, Juan Pérez</h2>
                <p>Panel de administración del sistema bancario</p>
            </div>
            
            <div class="dashboard-cards">
                <div class="card">
                    <h3>Total Clientes</h3>
                    <p>124</p>
                </div>
                <div class="card">
                    <h3>Préstamos Activos</h3>
                    <p>45</p>
                </div>
                <div class="card">
                    <h3>Préstamos Pendientes</h3>
                    <p>12</p>
                </div>
                <div class="card">
                    <h3>Nuevos Clientes (Mes)</h3>
                    <p>8</p>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
</body>
</html> 