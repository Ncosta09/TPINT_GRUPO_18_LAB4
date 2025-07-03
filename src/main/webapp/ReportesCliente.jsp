<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Reportes</title>
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
	<link rel="stylesheet" href="CSS/common.css" type="text/css" />
	<link rel="stylesheet" href="CSS/reportes.css" />
</head>
<body>
<jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarAdmin.jsp" />
       
           <div class="main-container-reportes">
        <!-- Panel de Parámetros -->
        <div class="card-reportes">
            <div class="card-header-reportes">
                <h2 class="card-title-reportes">
                    <i class="fas fa-chart-bar"></i>
                    Parámetros del Reporte
                </h2>
                <p class="card-description-reportes">
                    Selecciona los parámetros para generar tu reporte financiero personalizado
                </p>
            </div>
            <div class="card-content-reportes">
                <form id="reportForm" class="form-grid-reportes">
                    <div class="form-group-reportes">
                        <label for="startDate" class="form-label-reportes">Fecha de Inicio</label>
                        <input type="date" id="startDate" name="startDate" class="form-input-reportes" required>
                    </div>

                    <div class="form-group-reportes">
                        <label for="endDate" class="form-label-reportes">Fecha de Fin</label>
                        <input type="date" id="endDate" name="endDate" class="form-input-reportes" required>
                    </div>

                    <div class="form-group-reportes">
                        <button type="submit" id="generateBtn" class="btn-reportes btn-primary-reportes">
                            <span id="btnText">Generar Reporte</span>
                            <i id="loadingIcon" class="fas fa-spinner fa-spin" style="display: none;"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Loading State -->
        <div id="loadingState" class="card-reportes" style="display: none;">
            <div class="card-content-reportes loading-content-reportes">
                <i class="fas fa-spinner fa-spin loading-spinner-reportes"></i>
                <h3>Generando Reporte...</h3>
                <p>Por favor espera mientras procesamos la información</p>
            </div>
        </div>

        <!-- Empty State -->
        <div id="emptyState" class="card-reportes">
            <div class="card-content-reportes empty-content-reportes">
                <i class="fas fa-chart-bar empty-icon-reportes"></i>
                <h3>Generar Reporte</h3>
                <p>Selecciona los parámetros y haz clic en "Generar Reporte" para ver los resultados</p>
            </div>
        </div>

        <!-- Resultados del Reporte -->
        <div id="reportResults" style="display: none;">
            <!-- Resumen Ejecutivo -->
            <div class="stats-grid-reportes">
                <div class="stat-card-reportes stat-income-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Total Ingresos</span>
                        <i class="fas fa-trending-up stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="totalIngresos" class="stat-value-reportes">€0.00</div>
                        <p class="stat-period-reportes">Período: <span id="periodText"></span></p>
                    </div>
                </div>

                <div class="stat-card-reportes stat-expense-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Total Egresos</span>
                        <i class="fas fa-trending-down stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="totalEgresos" class="stat-value-reportes">€0.00</div>
                        <p class="stat-period-reportes">Período: <span id="periodText2"></span></p>
                    </div>
                </div>

                <div class="stat-card-reportes stat-balance-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Balance Neto</span>
                        <i class="fas fa-dollar-sign stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="balanceNeto" class="stat-value-reportes">€0.00</div>
                        <p class="stat-period-reportes">Diferencia I/E</p>
                    </div>
                </div>

                <div class="stat-card-reportes stat-transactions-reportes">
                    <div class="stat-header-reportes">
                        <span class="stat-title-reportes">Transacciones</span>
                        <i class="fas fa-list stat-icon-reportes"></i>
                    </div>
                    <div class="stat-content-reportes">
                        <div id="totalTransacciones" class="stat-value-reportes">0</div>
                        <p class="stat-period-reportes">Total operaciones</p>
                    </div>
                </div>
            </div>

            <!-- Gráfico Visual -->
            <div class="card-reportes">
                <div class="card-header-reportes">
                    <h2 class="card-title-reportes">Análisis Visual</h2>
                    <p class="card-description-reportes">Distribución de ingresos vs egresos en el período seleccionado</p>
                </div>
                <div class="card-content-reportes">
                    <div class="chart-container-reportes">
                        <div class="chart-visual-reportes">
                            <div class="chart-item-reportes">
                                <div id="incomeChart" class="chart-circle-reportes chart-income-reportes">
                                    <span id="incomePercentage">0%</span>
                                </div>
                                <p class="chart-label-reportes">Ingresos</p>
                            </div>
                            <div class="chart-item-reportes">
                                <div id="expenseChart" class="chart-circle-reportes chart-expense-reportes">
                                    <span id="expensePercentage">0%</span>
                                </div>
                                <p class="chart-label-reportes">Egresos</p>
                            </div>
                        </div>
                        <p class="chart-description-reportes">Representación visual de la distribución financiera</p>
                    </div>
                </div>
            </div>

            <!-- Detalle de Transacciones -->
            <div class="card-reportes">
                <div class="card-header-reportes">
                    <h2 class="card-title-reportes">Detalle de Transacciones</h2>
                    <p class="card-description-reportes">Listado completo de operaciones en el período seleccionado</p>
                </div>
                <div class="card-content-reportes">
                    <div class="table-container-reportes">
                        <table class="data-table-reportes">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Tipo</th>
                                    <th>Categoría</th>
                                    <th>Descripción</th>
                                    <th class="text-right-reportes">Monto</th>
                                </tr>
                            </thead>
                            <tbody id="transactionsTableBody">
                                <!-- Las filas se llenarán dinámicamente -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Análisis por Categorías -->
            <div class="card-reportes">
                <div class="card-header-reportes">
                    <h2 class="card-title-reportes">Análisis por Categorías</h2>
                    <p class="card-description-reportes">Resumen de movimientos agrupados por categoría</p>
                </div>
                <div class="card-content-reportes">
                    <div id="categoriesAnalysis" class="categories-grid-reportes">
                        <!-- Las categorías se llenarán dinámicamente -->
                    </div>
                </div>
            </div>
        </div>
    </div>
        
    </div>
    
     <!-- Declaracion del script del Sidebar -->
    <script src="JS/script.js"></script>
    <script src="js/common.js"></script>

</body>
</html>