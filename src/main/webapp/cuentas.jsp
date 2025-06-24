<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Bancaria - Mis Cuentas</title>
    <link rel="stylesheet" href="CSS/common.css" type="text/css" />
    <link rel="stylesheet" href="CSS/cuentas.css" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />
        
        <div class="overlay" id="overlay"></div>
        
        <div class="content">
            <div class="accounts-container">
                <h2 class="section-title">
                    <span class="section-icon">💰</span>
                    Mis Cuentas
                </h2>
                
                <table id="accountsTable">
                    <thead>
                        <tr>
                            <th>Número de Cuenta</th>
                            <th>Tipo</th>
                            <th>Saldo</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1234567890</td>
                            <td>Cuenta Corriente</td>
                            <td>$25,430.50</td>
                            <td><span class="status-active">Activa</span></td>
                            <td>
                                <button class="btn btn-sm" onclick="viewMovements('1234567890')">Ver Movimientos</button>
                            </td>
                        </tr>
                        <tr>
                            <td>0987654321</td>
                            <td>Caja de Ahorro</td>
                            <td>$19,800.00</td>
                            <td><span class="status-active">Activa</span></td>
                            <td>
                                <button class="btn btn-sm" onclick="viewMovements('0987654321')">Ver Movimientos</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="movements-section" id="movementsSection">
                <h3 class="section-title">
                    <span class="section-icon">📊</span>
                    Movimientos de la Cuenta: <span id="selectedAccount"></span>
                </h3>
                
                <div class="filter-container">
                    <select class="filter-select" id="movementType">
                        <option value="">Todos los tipos</option>
                        <option value="transferencia">Transferencia</option>
                        <option value="deposito">Depósito</option>
                        <option value="retiro">Retiro</option>
                        <option value="pago">Pago</option>
                    </select>
                    
                    <input type="date" class="filter-input" id="startDate" placeholder="Fecha desde">
                    <input type="date" class="filter-input" id="endDate" placeholder="Fecha hasta">
                    
                    <button class="btn" onclick="filterMovements()">Filtrar</button>
                    <button class="btn btn-secondary" onclick="closeMovements()">Cerrar</button>
                </div>
                
                <table id="movementsTable">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Descripción</th>
                            <th>Tipo</th>
                            <th>Monto</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody id="movementsTableBody">
                        <!-- Los movimientos se cargarán dinámicamente -->
                    </tbody>
                </table>
                
                <div class="pagination" id="pagination">
                    <button onclick="changePage(-1)">Anterior</button>
                    <span id="pageInfo">Página 1 de 1</span>
                    <button onclick="changePage(1)">Siguiente</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/common.js"></script>
    <script src="js/cuentas.js"></script>
</body>
</html>
