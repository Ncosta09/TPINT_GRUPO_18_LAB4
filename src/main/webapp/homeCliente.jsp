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
                <h2>Bienvenido, <%= request.getAttribute("nombreCliente") %></h2>
                <p>Panel de cliente del sistema bancario</p>
            </div>
            
            <div class="dashboard-cards">
                <div class="card" onclick="window.location.href='ServletCuentasCliente'">
                    <div class="card-icon">💰</div>
                    <h3>Saldo Total</h3>
                    <p>$<%= String.format("%,.2f", request.getAttribute("saldoTotal")) %></p>
                </div>
                <div class="card" onclick="window.location.href='ServletTransferencia'">
                    <div class="card-icon">💸</div>
                    <h3>Transferencias (Mes)</h3>
                    <p><%= request.getAttribute("transferenciasMes") %></p>
                </div>
                <div class="card" onclick="window.location.href='ServletPedirPrestamo'">
                    <div class="card-icon">📋</div>
                    <h3>Préstamos Activos</h3>
                    <p><%= request.getAttribute("prestamosActivos") %></p>
                </div>
                <div class="card" onclick="window.location.href='ServletPagarCuotas'">
                    <div class="card-icon">📊</div>
                    <h3>Cuotas Pendientes</h3>
                    <p><%= request.getAttribute("cuotasPendientes") %></p>
                </div>
            </div>
            
            <div class="quick-actions">
                <div class="action-card" onclick="window.location.href='ServletTransferencia'">
                    <h4>Nueva Transferencia</h4>
                    <p>Realizar transferencia entre cuentas</p>
                </div>
                <div class="action-card" onclick="window.location.href='ServletPedirPrestamo'">
                    <h4>Solicitar Préstamo</h4>
                    <p>Nueva solicitud de préstamo</p>
                </div>
                <div class="action-card" onclick="window.location.href='ServletPagarCuotas'">
                    <h4>Pagar Cuotas</h4>
                    <p>Gestionar pagos de préstamos</p>
                </div>
                <div class="action-card" onclick="window.location.href='ServletPerfil'">
                    <h4>Mi Perfil</h4>
                    <p>Ver y editar información personal</p>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
</body>
</html> 