<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario" %>

<%
    
    Usuario usuarioLogueado = null;
    String textoBienvenida = "No ha iniciado sesión";

    if (session != null && session.getAttribute("usuarioLogueado") != null) {
        usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        textoBienvenida = "Usuario: " + usuarioLogueado.getNombreUsuario();
    }
%>
<div class="header">
        <div class="burger-menu" id="burgerMenu">☰</div>
        <h1>Sistema de Gestión Bancaria</h1>
        <div class="user-info">
            <span class="user-name"><%= textoBienvenida %></span>
            <a href="Logout" style="color: white; text-decoration: none;">Salir</a>
        </div>
    </div>