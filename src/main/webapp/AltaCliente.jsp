<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sistema de Gestión Bancaria - Alta de Cliente</title>
    <link rel="stylesheet" href="CSS/altaCli.css" />
</head>
<body>
    <jsp:include page="Header.jsp" />

    <div class="container">
        <div class="sidebar" id="sidebar">
            <ul class="sidebar-menu">
                <li><a href="#" class="active">Alta de Usuario/Cliente</a></li>
                <li><a href="#">Lista de Clientes</a></li>
                <li><a href="#">Gestión de Préstamos</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>

        <div class="overlay" id="overlay"></div>

        <div class="content">
            <div class="form-container">
                <h2 class="form-title">Alta de Cliente</h2>
                <form id="clientForm" action="altaCliente" method="post">
                     <div class="form-row">
                        <div class="form-group">
                            <label for="dni">DNI:</label>
                            <input type="text" id="dni" name="dni" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="cuil">CUIL:</label>
                            <input type="text" id="cuil" name="cuil" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="sexo">Sexo:</label>
                            <select id="sexo" name="sexo" required>
                                <option value="">Seleccione una...</option>
                                <option value="M">Masculino</option>
                                <option value="F">Femenino</option>
                                <option value="O">Otro</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="nacionalidad">Nacionalidad:</label>
                            <input type="text" id="nacionalidad" name="nacionalidad" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="direccion">Dirección:</label>
                            <input type="text" id="direccion" name="direccion" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="localidad">Localidad:</label>
                            <input type="text" id="localidad" name="localidad" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="provincia">Provincia:</label>
                            <select id="provincia" name="provincia" required>
                                <option value="">Seleccione una...</option>
                                <option value="BSAS">Buenos Aires</option>
                                <option value="CABA">Ciudad Autónoma de Buenos Aires</option>
                                <option value="CBA">Córdoba</option>
                                <option value="SFE">Santa Fe</option>
                                <option value="MZA">Mendoza</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="tel" id="telefono" name="telefono" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="usuario">Usuario:</label>
                            <input type="text" id="usuario" name="usuario" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="clave">Contraseña:</label>
                            <input type="password" id="clave" name="clave" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="confirmClave">Confirmar Contraseña:</label>
                            <input type="password" id="confirmClave" name="confirmClave" required>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="btn btn-secondary" id="cancelBtn">Cancelar</button>
                        <button type="submit" class="btn" id="submitBtn">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="notification" id="notification">Cliente guardado correctamente</div>

    <script src="JS/AltaCli.js"></script>
</body>
</html>
