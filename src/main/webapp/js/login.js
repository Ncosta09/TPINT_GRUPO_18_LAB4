document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');
    const errorAlert = document.querySelector('.error-alert');
    
    // ocultar alerta a los 5 segundos
    if (errorAlert) {
        setTimeout(() => {
            errorAlert.style.animation = 'slideUp 0.3s ease-out forwards';
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
    
    // Función para mostrar errores del lado cliente
    function showClientError(message) {
        // Remover alerta existente si hay una
        const existingAlert = document.querySelector('.error-alert');
        if (existingAlert) {
            existingAlert.remove();
        }
        
        // Crear nueva alerta
        const alert = document.createElement('div');
        alert.className = 'error-alert';
        alert.innerHTML = `
            <span class="error-icon">⚠️</span>
            ${message}
        `;
        
        // Insertar después del h2
        const h2 = document.querySelector('.login-form h2');
        h2.insertAdjacentElement('afterend', alert);
        
        // ocultar después de 3 segundos
        setTimeout(() => {
            alert.style.animation = 'slideUp 0.3s ease-out forwards';
            setTimeout(() => alert.remove(), 300);
        }, 3000);
    }
});

// Agregar animación slideUp al CSS
const style = document.createElement('style');
style.textContent = `
    @keyframes slideUp {
        from {
            opacity: 1;
            transform: translateY(0);
        }
        to {
            opacity: 0;
            transform: translateY(-10px);
        }
    }
`;
document.head.appendChild(style);