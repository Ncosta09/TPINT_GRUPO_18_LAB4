<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <h2 class="table-title">
                        <span class="table-icon">🏦</span>
                        Lista de Cuentas Bancarias
                    </h2>
                    <div class="search-container">
                        <input type="text" class="search-input" id="searchInput" placeholder="Buscar por cliente...">
                        <button class="btn" id="searchBtn">Buscar</button>
                    </div>
                </div>
                
                <div class="filter-container">
                    <select class="filter-select" id="tipoFilter">
                        <option value="">Todos los tipos</option>
                        <option value="Caja de Ahorro">Caja de Ahorro</option>
                        <option value="Cuenta Corriente">Cuenta Corriente</option>
                    </select>
                    
                    <select class="filter-select" id="estadoFilter">
                        <option value="">Todos los estados</option>
                        <option value="Activa">Activa</option>
                        <option value="Inactiva">Inactiva</option>
                    </select>
                    
                    <button class="btn" id="filterBtn">Filtrar</button>
                    <button class="btn btn-success" onclick="window.location.href='AltaCuentas.jsp'">+ Nueva Cuenta</button>
                </div>
                
                <table>
                    <thead>
                        <tr>
                            <th>Cliente</th>
                            <th>Número de Cuenta</th>
                            <th>CBU</th>
                            <th>Tipo de Cuenta</th>
                       <!--      <th>Fecha Creación</th> -->
                            <th>Saldo</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody id="cuentasTableBody">
                    
                    
   <%@ page import="java.util.List, dominio.Cuenta,java.util.ArrayList" %>
<%
List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
if (cuentas == null) cuentas = new ArrayList<>();
for (Cuenta c : cuentas) {
%>
      <tr>
        <form action="ServletCuenta" method="post">
          <input type="hidden" name="accion" value="actualizar" />
          <input type="hidden" name="id" value="<%= c.getId() %>" />

          <td><%= c.getCliente().getNombre() +", "+ c.getCliente().getApellido() %></td>
          <td><input type="text" name="numeroCuenta" value="<%= c.getNumeroCuenta() %>" readonly /></td>
          <td><input type="text" name="cbu" value="<%= c.getCbu() %>" readonly /></td>
          <td class="account-type-cell">
            <select name="tipoCuenta" class="account-type-select">
              <option value="CA" <%= c.getTipoCuenta().equals("CA") ? "selected" : "" %>>Caja de Ahorro</option>
              <option value="CC" <%= c.getTipoCuenta().equals("CC") ? "selected" : "" %>>Cuenta Corriente</option>
            </select>
          </td>
   <%-- <td><input type="date" name="fechaCreacion" value="<%= c.getFechaCreacion() %>" readonly /></td> --%>
<td>
                                <input type="text" class="saldo-input" value="<%= c.getSaldo() %>" data-original="156780.90">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
          
        </form>
      </tr>
    <%
      }
    %>
                    
                    <!-- 
                        <tr data-cliente="Juan Gómez" data-tipo="Caja de Ahorro" data-estado="Activa">
                            <td>Juan Gómez</td>
                            <td>CA-10001234</td>
                            <td>0110012300010001234901</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CA" data-cliente="Juan Gómez">
                                    <option value="CA" selected>Caja de Ahorro</option>
                                    <option value="CC">Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>15/01/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$45,230.50" data-original="45230.50">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="María López" data-tipo="Cuenta Corriente" data-estado="Activa">
                            <td>María López</td>
                            <td>CC-10002345</td>
                            <td>0110012300010002345902</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CC" data-cliente="María López">
                                    <option value="CA">Caja de Ahorro</option>
                                    <option value="CC" selected>Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>20/01/2023</td>
                            <td>
	                            <input type="text" class="saldo-input" value="$45,230.50" data-original="45230.50">
	                            <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Carlos Rodríguez" data-tipo="Caja de Ahorro" data-estado="Activa">
                            <td>Carlos Rodríguez</td>
                            <td>CA-10003456</td>
                            <td>0110012300010003456903</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CA" data-cliente="Carlos Rodríguez">
                                    <option value="CA" selected>Caja de Ahorro</option>
                                    <option value="CC">Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>25/01/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$125,670.25" data-original="125670.25">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Laura Martínez" data-tipo="Caja de Ahorro" data-estado="Activa">
                            <td>Laura Martínez</td>
                            <td>CA-10004567</td>
                            <td>0110012300010004567904</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CA" data-cliente="Laura Martínez">
                                    <option value="CA" selected>Caja de Ahorro</option>
                                    <option value="CC">Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>02/02/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$32,450.75" data-original="32450.75">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Laura Martínez" data-tipo="Cuenta Corriente" data-estado="Activa">
                            <td>Laura Martínez</td>
                            <td>CC-10004568</td>
                            <td>0110012300010004568905</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CC" data-cliente="Laura Martínez">
                                    <option value="CA">Caja de Ahorro</option>
                                    <option value="CC" selected>Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>02/02/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$89,320.00" data-original="89320.00">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Pedro Sánchez" data-tipo="Cuenta Corriente" data-estado="Inactiva">
                            <td>Pedro Sánchez</td>
                            <td>CC-10005678</td>
                            <td>0110012300010005678906</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CC" data-cliente="Pedro Sánchez">
                                    <option value="CA">Caja de Ahorro</option>
                                    <option value="CC" selected>Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>10/02/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$0.00" data-original="0.00">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Inactiva">
                                    <option value="Activa">Activa</option>
                                    <option value="Inactiva" selected>Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Ana Fernández" data-tipo="Caja de Ahorro" data-estado="Activa">
                            <td>Ana Fernández</td>
                            <td>CA-10006789</td>
                            <td>0110012300010006789907</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CA" data-cliente="Ana Fernández">
                                    <option value="CA" selected>Caja de Ahorro</option>
                                    <option value="CC">Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>15/02/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$67,890.30" data-original="67890.30">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr>
                        <tr data-cliente="Roberto Díaz" data-tipo="Cuenta Corriente" data-estado="Activa">
                            <td>Roberto Díaz</td>
                            <td>CC-10007890</td>
                            <td>0110012300010007890908</td>
                            <td class="account-type-cell">
                                <select class="account-type-select" data-original="CC" data-cliente="Roberto Díaz">
                                    <option value="CA">Caja de Ahorro</option>
                                    <option value="CC" selected>Cuenta Corriente</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>20/02/2023</td>
                            <td>
                                <input type="text" class="saldo-input" value="$156,780.90" data-original="156780.90">
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                            <td>
                                <select class="estado-select" data-original="Activa">
                                    <option value="Activa" selected>Activa</option>
                                    <option value="Inactiva">Inactiva</option>
                                </select>
                                <button class="save-btn" title="Guardar cambio">💾</button>
                            </td>
                        </tr> -->
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
