<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>

<%
  Cuenta c = (Cuenta) request.getAttribute("cuenta");
  String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Modificar Cuenta</title>
    <link rel="stylesheet" href="CSS/editarCuenta.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="form-container">
                <h2 class="form-title">Modificar Cuenta</h2>
                
                <% if (error != null) { %>
                    <div class="error-alert"><%= error %></div>
                <% } %>
                
                <form action="${pageContext.request.contextPath}/ServletModificarCuenta" method="post" id="cuentaForm">
                    <input type="hidden" name="idCuenta" value="<%= c.getId() %>">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cliente">Cliente:</label>
                            <input type="text" id="cliente" value="<%= c.getCliente().getNombre() + " " + c.getCliente().getApellido() %>" readonly class="readonly-field">
                        </div>
                        
                        <div class="form-group">
                            <label for="numeroCuenta">Número de Cuenta:</label>
                            <input type="text" id="numeroCuenta" value="<%= c.getNumeroCuenta() %>" readonly class="readonly-field">
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cbu">CBU:</label>
                            <input type="text" id="cbu" value="<%= c.getCbu() %>" readonly class="readonly-field">
                        </div>
                        
                        <div class="form-group">
                            <label for="tipoCuenta">Tipo de Cuenta:</label>
                            <input type="text" id="tipoCuenta" value="<%= c.getTipoCuenta() %>" readonly class="readonly-field">
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="saldo">Saldo:</label>
                            <input type="number" step="0.01" id="saldo" name="saldo" value="<%= c.getSaldo() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="estado">Estado:</label>
                            <select id="estado" name="estado" required>
                                <option value="Activa" <%= c.isEstado() ? "selected" : "" %>>Activa</option>
                                <option value="Inactiva" <%= !c.isEstado() ? "selected" : "" %>>Inactiva</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='ServletListaCuentas'">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification">Cuenta modificada correctamente</div>
</body>
</html>