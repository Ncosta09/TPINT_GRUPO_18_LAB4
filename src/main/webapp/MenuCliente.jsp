<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String activePage = (String) request.getAttribute("activePage");
    if (activePage == null) activePage = "";
%>

<div class="sidebar" id="sidebar">
    <ul class="sidebar-menu">
        <li><a href="ServletHomeCliente" class="<%= "home".equals(activePage) ? "active" : "" %>">Inicio</a></li>
        <li><a href="verPerfil.jsp" class="<%= "perfil".equals(activePage) ? "active" : "" %>">Ver Perfil</a></li>
        <li><a href="cuentas.jsp" class="<%= "cuentas".equals(activePage) ? "active" : "" %>">Ver Cuentas</a></li>
        <li><a href="transferencias.jsp" class="<%= "transferencias".equals(activePage) ? "active" : "" %>">Transferencias</a></li>
        <li><a href="pedirPrestamo.jsp" class="<%= "prestamo".equals(activePage) ? "active" : "" %>">Pedir Préstamo</a></li>
        <li><a href="pagarCuotas.jsp" class="<%= "cuotas".equals(activePage) ? "active" : "" %>">Pagar Cuotas</a></li>
        <li><a href="cuentas.jsp" class="<%= "cuentas".equals(activePage) ? "active" : "" %>">Ver Cuentas</a></li>
        <li><a href="transferencias.jsp" class="<%= "transferencias".equals(activePage) ? "active" : "" %>">Transferencias</a></li>
    </ul>
</div>
