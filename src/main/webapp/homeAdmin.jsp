<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="dominio.Usuario" %>
<%
    
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
%>
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
                <h2>Bienvenido, Admin</h2>
                <p>Panel de administración del sistema bancario</p>
            </div>
            
            <div class="dashboard-cards">
			    <div class="card">
			        <h3>Total Clientes</h3>
			        <p><%= request.getAttribute("totalClientes") %></p>
			    </div>
			    <div class="card">
			        <h3>Préstamos Activos</h3>
			        <p>0</p>
			    </div>
			    <div class="card">
			        <h3>Préstamos Pendientes</h3>
			        <p>0</p>
			    </div>
			    <div class="card">
			        <h3>Nuevos Clientes (Mes)</h3>
			        <p><%= request.getAttribute("totalClientesMes") %></p>
			    </div>
			</div>
        </div>
    </div>

    <script src="js/common.js"></script>
</body>
</html> 