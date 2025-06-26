<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Alta de Cuenta</title>
    <link rel="stylesheet" href="CSS/altaCuentas.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
   
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="form-container">
                <h2 class="form-title">
                    <span class="form-icon">🏦</span>
                    Alta de Cuenta Bancaria
                </h2>
                <% String mensaje = (String) request.getAttribute("mensaje"); %>
<% if (mensaje != null) { %>
    <div class="alert alert-success">
        <%= mensaje %>
    </div>
<% } %>
                
                <form id="accountForm" action="CuentaServlet?accion=alta" method="post">
                    <div class="form-group">
                        <label for="cliente">Cliente:</label> 
                        
                        <%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>

<select id="cliente" name="cliente" required>
    <option value="">Seleccione un cliente...</option>
    <%
        List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
        if (listaClientes != null) {
            for (Cliente cli : listaClientes) {
    %>
    <option value="<%= cli.getIdCliente() %>">
        <%= cli.getNombre() %> <%= cli.getApellido() %> (DNI: <%= cli.getDni() %>)
    </option>
    <%
            }
        }
    %>
</select>



						<div class="error-message" id="cliente-error">Debe seleccionar un cliente</div>
                    </div>
                    
                    <div class="form-group">
                        <label for="tipoCuenta">Tipo de Cuenta:</label>
                        <select id="tipoCuenta" name="tipoCuenta" required>
                            <option value="">Seleccione tipo de cuenta...</option>
                            <option value="Caja de ahorro">Caja de Ahorro</option>
                            <option value="Cuenta corriente">Cuenta Corriente</option>
                        </select>
                        <div class="error-message" id="tipoCuenta-error">Debe seleccionar un tipo de cuenta</div>
                    </div>
                    
                    <div class="info-box">
                        <p>Saldo inicial de la cuenta:</p>
                        <div class="amount">$10,000.00</div>
                    </div>
                    
                    <div class="account-preview" id="accountPreview">
                        <h4>Vista Previa de la Cuenta</h4>
                        <div class="preview-item">
                            <span>Cliente:</span>
                            <span id="previewCliente">-</span>
                        </div>
                        <div class="preview-item">
                            <span>Tipo de Cuenta:</span>
                            <span id="previewTipo">-</span>
                        </div>
                        <div class="preview-item">
                            <span>Número de Cuenta:</span>
                            <span id="previewNumero">Se generará automáticamente</span>
                        </div>
                        <div class="preview-item">
                            <span>CBU:</span>
                            <span id="previewCBU">Se generará automáticamente</span>
                        </div>
                        <div class="preview-item highlight">
                            <span>Saldo Inicial:</span>
                            <span>$10,000.00</span>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" id="cancelBtn">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Crear Cuenta</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="notification" id="notification">Cuenta creada exitosamente</div>
     <script src="js/altaCuenta.js"></script>
</body>
</html>
