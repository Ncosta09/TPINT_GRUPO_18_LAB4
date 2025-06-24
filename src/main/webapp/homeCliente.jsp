<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Cliente</title>
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
    <link rel="stylesheet" href="CSS/homeCliente.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="welcome-card">
                <h2>Bienvenido, María González</h2>
                <p>Panel de cliente del sistema bancario</p>
            </div>
            
            <div class="dashboard-cards">
                <div class="card" onclick="window.location.href='cuentas.jsp'">
                    <div class="card-icon">💰</div>
                    <h3>Saldo Total</h3>
                    <p>$45,230.50</p>
                </div>
                <div class="card" onclick="window.location.href='transferencias.jsp'">
                    <div class="card-icon">💸</div>
                    <h3>Transferencias (Mes)</h3>
                    <p>8</p>
                </div>
                <div class="card" onclick="window.location.href='pedirPrestamo.jsp'">
                    <div class="card-icon">📋</div>
                    <h3>Préstamos Activos</h3>
                    <p>2</p>
                </div>
                <div class="card" onclick="window.location.href='pagarCuotas.jsp'">
                    <div class="card-icon">📊</div>
                    <h3>Cuotas Pendientes</h3>
                    <p>5</p>
                </div>
            </div>
            
            <div class="quick-actions">
                <div class="action-card" onclick="window.location.href='transferencias.jsp'">
                    <h4>Nueva Transferencia</h4>
                    <p>Realizar transferencia entre cuentas</p>
                </div>
                <div class="action-card" onclick="window.location.href='pedirPrestamo.jsp'">
                    <h4>Solicitar Préstamo</h4>
                    <p>Nueva solicitud de préstamo</p>
                </div>
                <div class="action-card" onclick="window.location.href='pagarCuotas.jsp'">
                    <h4>Pagar Cuotas</h4>
                    <p>Gestionar pagos de préstamos</p>
                </div>
                <div class="action-card" onclick="window.location.href='verPerfil.jsp'">
                    <h4>Mi Perfil</h4>
                    <p>Ver y editar información personal</p>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
</body>
</html> 