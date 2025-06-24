<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
	<link rel="stylesheet" href="CSS/prestamosAdmin.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="table-container">
                <div class="table-header">
                    <h2 class="table-title">Gestión de Préstamos</h2>
                    <div class="search-container">
                        <input type="text" class="search-input" id="searchInput" placeholder="Buscar préstamo...">
                        <button class="btn" onclick="searchLoans()">Buscar</button>
                    </div>
                </div>
                
                <div class="filter-container">
                    <select class="filter-select" id="statusFilter">
                        <option value="">Todos los estados</option>
                        <option value="pendiente">Pendiente</option>
                        <option value="aprobado">Aprobado</option>
                        <option value="rechazado">Rechazado</option>
                        <option value="activo">Activo</option>
                        <option value="finalizado">Finalizado</option>
                    </select>
                    
                    <select class="filter-select" id="typeFilter">
                        <option value="">Todos los tipos</option>
                        <option value="personal">Personal</option>
                        <option value="hipotecario">Hipotecario</option>
                        <option value="automotriz">Automotriz</option>
                        <option value="emprendimiento">Emprendimiento</option>
                    </select>
                    
                    <button class="btn" onclick="filterLoans()">Filtrar</button>
                    <button class="btn btn-secondary" onclick="clearFilters()">Limpiar</button>
                </div>
                
                <table id="loansTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Tipo</th>
                            <th>Monto</th>
                            <th>Plazo</th>
                            <th>Estado</th>
                            <th>Fecha Solicitud</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="loansTableBody">
                        <tr>
                            <td>PR001</td>
                            <td>María González</td>
                            <td>Personal</td>
                            <td>$50,000.00</td>
                            <td>24 meses</td>
                            <td><span class="badge badge-pending">Pendiente</span></td>
                            <td>10/01/2024</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewLoan('PR001')">Ver</button>
                                <button class="btn btn-sm btn-success" onclick="approveLoan('PR001')">Aprobar</button>
                                <button class="btn btn-sm btn-danger" onclick="rejectLoan('PR001')">Rechazar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR002</td>
                            <td>Juan Pérez</td>
                            <td>Hipotecario</td>
                            <td>$500,000.00</td>
                            <td>60 meses</td>
                            <td><span class="badge badge-approved">Aprobado</span></td>
                            <td>05/01/2024</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewLoan('PR002')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="editLoan('PR002')">Editar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>PR003</td>
                            <td>Ana López</td>
                            <td>Automotriz</td>
                            <td>$100,000.00</td>
                            <td>36 meses</td>
                            <td><span class="badge badge-active">Activo</span></td>
                            <td>01/01/2024</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewLoan('PR003')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="editLoan('PR003')">Editar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <script src="js/prestamosAdmin.js"></script>
</body>
</html> 