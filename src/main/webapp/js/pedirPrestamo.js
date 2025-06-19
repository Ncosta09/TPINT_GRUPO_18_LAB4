document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('prestamoForm');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            // Validación básica
            const monto = document.getElementById('monto').value;
            const plazo = document.getElementById('plazo').value;
            const motivo = document.getElementById('motivo').value;
            
            if (!monto || monto <= 0) {
                e.preventDefault();
                alert('El monto debe ser mayor a 0');
                return;
            }
            
            if (!plazo || plazo <= 0) {
                e.preventDefault();
                alert('El plazo debe ser mayor a 0');
                return;
            }
            
            if (!motivo.trim()) {
                e.preventDefault();
                alert('Debe especificar el motivo del préstamo');
                return;
            }
        });
    }
    
    // Botón de solicitar préstamo
    const btnSolicitarPrestamo = document.getElementById('btnSolicitarPrestamo');
    if (btnSolicitarPrestamo) {
        btnSolicitarPrestamo.addEventListener('click', function() {
            // Mostrar formulario de préstamo
            const formSection = document.getElementById('formSection');
            if (formSection) {
                formSection.style.display = 'block';
            }
        });
    }
});
