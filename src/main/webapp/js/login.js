document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');
    const errorAlert = document.querySelector('.error-alert');
    const errorContainer = document.querySelector('.error-container');
    
    // Si hay una alerta de error, marcar el contenedor y programar ocultado
    if (errorAlert && errorContainer) {
        errorContainer.classList.add('has-error');
        
        setTimeout(() => {
            hideErrorAlert(errorAlert, errorContainer);
        }, 5000);
    }
    
    // Agregar validación básica del lado cliente
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            const username = document.querySelector('input[name="nombreUsuario"]').value.trim();
            const password = document.querySelector('input[name="clave"]').value.trim();
            
            if (!username || !password) {
                e.preventDefault();
                showClientError('Por favor, complete todos los campos');
                return false;
            }
            
            // Mostrar indicador de carga
            const submitBtn = document.querySelector('.login-btn');
            const originalText = submitBtn.textContent;
            submitBtn.textContent = 'Iniciando sesión...';
            submitBtn.disabled = true;
            
            // Si hay error del servidor, restaurar el botón
            setTimeout(() => {
                submitBtn.textContent = originalText;
                submitBtn.disabled = false;
            }, 3000);
        });
    }
    
    // Función para ocultar alerta con transición suave
    function hideErrorAlert(alert, container) {
        alert.classList.add('hiding');
        
        setTimeout(() => {
            alert.remove();
            container.classList.remove('has-error');
        }, 300); // Esperar a que termine la transición
    }
    
    // Función para mostrar errores del lado cliente
    function showClientError(message) {
        const errorContainer = document.querySelector('.error-container');
        
        // Remover alerta existente si hay una
        const existingAlert = document.querySelector('.error-alert');
        if (existingAlert) {
            hideErrorAlert(existingAlert, errorContainer);
            
            // Esperar a que se oculte la anterior antes de mostrar la nueva
            setTimeout(() => {
                createAndShowAlert(message, errorContainer);
            }, 300);
        } else {
            createAndShowAlert(message, errorContainer);
        }
    }
    
    // Función para crear y mostrar nueva alerta
    function createAndShowAlert(message, container) {
        // Crear nueva alerta
        const alert = document.createElement('div');
        alert.className = 'error-alert';
        alert.innerHTML = `
            <span class="error-icon">⚠️</span>
            ${message}
        `;
        
        // Agregar al contenedor y marcar como activo
        container.appendChild(alert);
        container.classList.add('has-error');
        
        // Auto-ocultar después de 3 segundos
        setTimeout(() => {
            hideErrorAlert(alert, container);
        }, 3000);
    }
});