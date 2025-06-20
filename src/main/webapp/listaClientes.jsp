<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Panel Administrador</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/listaClientes.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="table-container">
                <div class="table-header">
                    <h2 class="table-title">Lista de Clientes</h2>
                    <div class="search-container">
                        <input type="text" class="search-input" id="searchInput" placeholder="Buscar cliente...">
                        <button class="btn" onclick="searchClients()">Buscar</button>
                    </div>
                </div>
                
                <div class="filter-container">
                    <select class="filter-select" id="statusFilter">
                        <option value="">Todos los estados</option>
                        <option value="activo">Activo</option>
                        <option value="inactivo">Inactivo</option>
                        <option value="suspendido">Suspendido</option>
                    </select>
                    
                    <select class="filter-select" id="typeFilter">
                        <option value="">Todos los tipos</option>
                        <option value="cliente">Cliente</option>
                        <option value="admin">Administrador</option>
                    </select>
                    
                    <button class="btn" onclick="filterClients()">Filtrar</button>
                    <button class="btn btn-secondary" onclick="clearFilters()">Limpiar</button>
                </div>
                
                <table id="clientsTable">
                    <thead>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Estado</th>
                            <th>Tipo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="clientsTableBody">
                        <tr>
                            <td>12345678</td>
                            <td>María González</td>
                            <td>maria.gonzalez@email.com</td>
                            <td>11-1234-5678</td>
                            <td><span class="badge badge-active">Activo</span></td>
                            <td>Cliente</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewClient('12345678')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="editClient('12345678')">Editar</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteClient('12345678')">Eliminar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>87654321</td>
                            <td>Juan Pérez</td>
                            <td>juan.perez@email.com</td>
                            <td>11-8765-4321</td>
                            <td><span class="badge badge-active">Activo</span></td>
                            <td>Administrador</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewClient('87654321')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="editClient('87654321')">Editar</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteClient('87654321')">Eliminar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>11223344</td>
                            <td>Ana López</td>
                            <td>ana.lopez@email.com</td>
                            <td>11-1122-3344</td>
                            <td><span class="badge badge-inactive">Inactivo</span></td>
                            <td>Cliente</td>
                            <td class="actions">
                                <button class="btn btn-sm" onclick="viewClient('11223344')">Ver</button>
                                <button class="btn btn-sm btn-secondary" onclick="editClient('11223344')">Editar</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteClient('11223344')">Eliminar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <script src="js/listaClientes.js"></script>
</body>
</html> 