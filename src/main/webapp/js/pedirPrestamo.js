document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loanForm');
    const montoInput = document.getElementById('monto');
    const plazoSelect = document.getElementById('plazo');
    const loanSummary = document.getElementById('loanSummary');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            // Validación básica
            const monto = montoInput.value;
            const plazo = plazoSelect.value;
            const destino = document.getElementById('destino').value;
            const cuentaDestino = document.getElementById('cuentaDestino').value;
            
            if (!monto || monto <= 0) {
                e.preventDefault();
                alert('El monto debe ser mayor a 0');
                return;
            }
            
            if (!plazo || plazo <= 0) {
                e.preventDefault();
                alert('Debe seleccionar un plazo válido');
                return;
            }
            
            if (!destino.trim()) {
                e.preventDefault();
                alert('Debe especificar el destino del préstamo');
                return;
            }
            
            if (!cuentaDestino) {
                e.preventDefault();
                alert('Debe seleccionar una cuenta de destino');
                return;
            }
            
            if (monto < 10000 || monto > 500000) {
                e.preventDefault();
                alert('El monto debe estar entre $10,000 y $500,000');
                return;
            }
        });
    }
    
    // Función para calcular y mostrar el resumen del préstamo
    function actualizarResumen() {
        const monto = parseFloat(montoInput.value) || 0;
        const plazo = parseInt(plazoSelect.value) || 0;
        
        if (monto > 0 && plazo > 0) {
            const tasaInteres = 0.15; // 15% anual
            const tasaMensual = tasaInteres / 12;
            const cuotaMensual = (monto * tasaMensual * Math.pow(1 + tasaMensual, plazo)) / (Math.pow(1 + tasaMensual, plazo) - 1);
            const totalPagar = cuotaMensual * plazo;
            
            document.getElementById('summaryMonto').textContent = '$' + monto.toLocaleString('es-AR', {minimumFractionDigits: 2, maximumFractionDigits: 2});
            document.getElementById('summaryPlazo').textContent = plazo + ' meses';
            document.getElementById('summaryCuota').textContent = '$' + cuotaMensual.toLocaleString('es-AR', {minimumFractionDigits: 2, maximumFractionDigits: 2});
            document.getElementById('summaryTotal').textContent = '$' + totalPagar.toLocaleString('es-AR', {minimumFractionDigits: 2, maximumFractionDigits: 2});
            
            loanSummary.style.display = 'block';
        } else {
            loanSummary.style.display = 'none';
        }
    }
    
    // Event listeners para actualizar el resumen
    if (montoInput) {
        montoInput.addEventListener('input', actualizarResumen);
    }
    
    if (plazoSelect) {
        plazoSelect.addEventListener('change', actualizarResumen);
    }
    
    // Validación en tiempo real del monto
    if (montoInput) {
        montoInput.addEventListener('blur', function() {
            const monto = parseFloat(this.value);
            const errorDiv = document.getElementById('monto-error');
            
            if (monto < 10000) {
                errorDiv.textContent = 'El monto mínimo es $10,000';
                errorDiv.style.display = 'block';
            } else if (monto > 500000) {
                errorDiv.textContent = 'El monto máximo es $500,000';
                errorDiv.style.display = 'block';
            } else {
                errorDiv.style.display = 'none';
            }
        });
    }
});
