document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('transferenciaForm');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            // Validación básica
            const monto = document.getElementById('monto').value;
            const cuentaDestino = document.getElementById('cuentaDestino').value;
            
            if (!monto || monto <= 0) {
                e.preventDefault();
                alert('El monto debe ser mayor a 0');
                return;
            }
            
            if (!cuentaDestino) {
                e.preventDefault();
                alert('Debe seleccionar una cuenta destino');
                return;
            }
        });
    }
    
    // Botón de nueva transferencia
    const btnNuevaTransferencia = document.getElementById('btnNuevaTransferencia');
    if (btnNuevaTransferencia) {
        btnNuevaTransferencia.addEventListener('click', function() {
            // Mostrar formulario de transferencia
            const formSection = document.getElementById('formSection');
            if (formSection) {
                formSection.style.display = 'block';
            }
        });
    }
}); 