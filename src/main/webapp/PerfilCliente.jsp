<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Sistema de Gestión Bancaria - Mi Perfil</title>

    <!-- Estilos -->
    <link rel="stylesheet" href="CSS/layout.css" />
    <link rel="stylesheet" href="CSS/perfil.css" />
</head>
<body>
	<jsp:include page="/header.jsp" />
    
    <div class="container">
        <jsp:include page="/sidebarCliente.jsp" />

        <div class="content">
            <div class="profile-container">
                <h2 class="profile-title">
                    <span class="profile-icon">👤</span>
                    Mi Perfil
                </h2>
                
                <div class="profile-section">
                    <h3 class="section-title">Información Personal</h3>
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">DNI:</span>
                            <div class="field-value">28.456.789</div>
                        </div>
                        
                        <div class="profile-field">
                            <span class="field-label">CUIL:</span>
                            <div class="field-value">27-28456789-3</div>
                        </div>
                    </div>
                    
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">Nombre:</span>
                            <div class="field-value">María</div>
                        </div>
                        
                        <div class="profile-field">
                            <span class="field-label">Apellido:</span>
                            <div class="field-value">González</div>
                        </div>
                    </div>
                    
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">Sexo:</span>
                            <div class="field-value">Femenino</div>
                        </div>
                        
                        <div class="profile-field">
                            <span class="field-label">Nacionalidad:</span>
                            <div class="field-value">Argentina</div>
                        </div>
                    </div>
                    
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">Fecha de Nacimiento:</span>
                            <div class="field-value">15/03/1985</div>
                        </div>
                    </div>
                </div>
                
                <div class="profile-section">
                    <h3 class="section-title">Información de Contacto</h3>
                    <div class="profile-row">
                        <div class="profile-field full-width">
                            <span class="field-label">Dirección:</span>
                            <div class="field-value">Av. Corrientes 1234, Piso 5, Depto B</div>
                        </div>
                    </div>
                    
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">Localidad:</span>
                            <div class="field-value">Ciudad Autónoma de Buenos Aires</div>
                        </div>
                        
                        <div class="profile-field">
                            <span class="field-label">Provincia:</span>
                            <div class="field-value">Ciudad Autónoma de Buenos Aires</div>
                        </div>
                    </div>
                    
                    <div class="profile-row">
                        <div class="profile-field">
                            <span class="field-label">Email:</span>
                            <div class="field-value">maria.gonzalez@email.com</div>
                        </div>
                        
                        <div class="profile-field">
                            <span class="field-label">Teléfono:</span>
                            <div class="field-value">+54 11 4567-8901</div>
                        </div>
                    </div>
                </div>
                
                <div style="text-align: center;">
                    <button type="button" class="btn btn-secondary" onclick="window.location.href='Home.jsp'">Volver al Inicio</button>
                </div>
            </div>
        </div>
    </div>
 <!-- Declaracion del script del Sidebar -->
    <script src="JS/script.js"></script>
</body>
</html>
