<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dominio.Cuenta, java.util.ArrayList" %>
<%@ page import="dominio.Usuario" %>
<%
    
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Lista de Cuentas</title>
    <link rel="stylesheet" href="CSS/listaCuentas.css" type="text/css" />
</head>
<body>
<jsp:include page="/header.jsp" />

<div class="container">
    <jsp:include page="/sidebarAdmin.jsp" />
    <div class="overlay" id="overlay"></div>

    <div class="content">
        <div class="table-container">
            <div class="table-header">
                <h2 class="table-title">🏦 Lista de Cuentas Bancarias</h2>
                <div class="search-container">
                    <input type="text" class="search-input" id="searchInput" placeholder="Buscar por cliente...">
                    <button class="btn" id="searchBtn">Buscar</button>
                </div>
            </div>

            <div class="filter-container">
                <select class="filter-select" id="tipoFilter">
                    <option value="">Todos los tipos</option>
                    <option value="caja de ahorro">Caja de Ahorro</option>
                    <option value="cuenta corriente">Cuenta Corriente</option>
                </select>

                <select class="filter-select" id="estadoFilter">
                    <option value="">Todos los estados</option>
                    <option value="activa">Activa</option>
                    <option value="inactiva">Inactiva</option>
                </select>

                <button class="btn" id="filterBtn">Filtrar</button>
                <button class="btn btn-success" onclick="window.location.href='ServletCuenta?accion=formAlta'">+ Nueva Cuenta</button>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Número de Cuenta</th>
                        <th>CBU</th>
                        <th>Tipo de Cuenta</th>
                        <th>Saldo</th>
                        <th>Estado</th> 
                    </tr>
                </thead>
                <tbody id="cuentasTableBody">
<%
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
    if (cuentas == null) cuentas = new ArrayList<>();

    for (Cuenta c : cuentas) {
%>
<tr data-cliente="<%= c.getCliente().getNombre() + " " + c.getCliente().getApellido() %>"
    data-tipo="<%= c.getTipoCuenta().trim().toLowerCase() %>"
    data-estado="<%= c.isEstado() ? "activa" : "inactiva" %>">
    <form action="ServletCuenta" method="post">
        <input type="hidden" name="accion" value="actualizar" />
        <input type="hidden" name="id" value="<%= c.getId() %>" />

        <td><%= c.getCliente().getNombre() + ", " + c.getCliente().getApellido() %></td>
        <td><input type="text" name="numeroCuenta" value="<%= c.getNumeroCuenta() %>" readonly /></td>
        <td><input type="text" name="cbu" value="<%= c.getCbu() %>" readonly /></td>
        <td><%= c.getTipoCuenta() %></td>
        <td>
            <input type="number" step="0.01" name="saldo" class="saldo-input" value="<%= c.getSaldo() %>" />
            <button type="button" class="save-btn" title="Guardar cambio">💾</button>
        </td>
        <td>
            <select name="estado" class="estado-select">
                <option value="Activa" <%= c.isEstado() ? "selected" : "" %>>Activa</option>
                <option value="Inactiva" <%= !c.isEstado() ? "selected" : "" %>>Inactiva</option>
            </select>
            <button type="button" class="save-btn" title="Guardar cambio">💾</button>
        </td>
    </form>
</tr>
<%
    }
%>
                </tbody>
            </table>

            <div class="pagination" id="pagination">
                <button class="active">1</button>
                <button>2</button>
                <button>3</button>
                <button>Siguiente</button>
            </div>
        </div>
    </div>
</div>

<div class="notification" id="notification"></div>
<script src="js/listaCuentas.js"></script>
</body>
</html>
