// Funcionalidad del menú móvil
document.addEventListener('DOMContentLoaded', function() {
    const burgerMenu = document.getElementById('burgerMenu');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');
    
    if (burgerMenu && sidebar) {
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            if (overlay) {
                overlay.classList.toggle('active');
            }
        });
        
        if (overlay) {
            overlay.addEventListener('click', function() {
                sidebar.classList.remove('active');
                overlay.classList.remove('active');
            });
        }
    }
    
    // Marcar el enlace activo en el sidebar
    const currentPage = window.location.pathname.split('/').pop();
    const sidebarLinks = document.querySelectorAll('.sidebar-menu a');
    
    sidebarLinks.forEach(link => {
        if (link.getAttribute('href') === currentPage) {
            link.classList.add('active');
        }
    });
});

// Funciones de utilidad
function showError(elementId, message) {
    const errorElement = document.getElementById(elementId);
    if (errorElement) {
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }
}

function hideError(elementId) {
    const errorElement = document.getElementById(elementId);
    if (errorElement) {
        errorElement.style.display = 'none';
    }
}

function showSuccess(elementId, message) {
    const successElement = document.getElementById(elementId);
    if (successElement) {
        successElement.textContent = message;
        successElement.style.display = 'block';
    }
}

function hideSuccess(elementId) {
    const successElement = document.getElementById(elementId);
    if (successElement) {
        successElement.style.display = 'none';
    }
}

function validateRequired(value, fieldName) {
    if (!value || value.trim() === '') {
        return `${fieldName} es obligatorio`;
    }
    return null;
}

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        return 'El formato del email no es válido';
    }
    return null;
} 