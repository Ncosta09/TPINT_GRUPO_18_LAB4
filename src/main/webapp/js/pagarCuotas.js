let currentPaymentData = null;

// Datos de ejemplo para las cuotas
const cuotasData = [
    {
        prestamo: 'PR001 - Personal',
        numero: 5,
        vencimiento: '15/01/2024',
        monto: 2500.00,
        estado: 'pendiente'
    },
    {
        prestamo: 'PR001 - Personal',
        numero: 6,
        vencimiento: '15/02/2024',
        monto: 2500.00,
        estado: 'pendiente'
    },
    {
        prestamo: 'PR002 - Hipotecario',
        numero: 12,
        vencimiento: '10/01/2024',
        monto: 8000.00,
        estado: 'vencido'
    },
    {
        prestamo: 'PR002 - Hipotecario',
        numero: 13,
        vencimiento: '10/02/2024',
        monto: 8000.00,
        estado: 'pendiente'
    }
];

function filterCuotas() {
    const prestamoFilter = document.getElementById('prestamoFilter').value;
    const estadoFilter = document.getElementById('estadoFilter').value;
    
    let filteredData = cuotasData;
    
    if (prestamoFilter) {
        filteredData = filteredData.filter(cuota => cuota.prestamo.includes(prestamoFilter));
    }
    
    if (estadoFilter) {
        filteredData = filteredData.filter(cuota => cuota.estado === estadoFilter);
    }
    
    displayCuotas(filteredData);
}

function clearFilters() {
    document.getElementById('prestamoFilter').value = '';
    document.getElementById('estadoFilter').value = '';
    displayCuotas(cuotasData);
}

function displayCuotas(cuotas) {
    const tbody = document.getElementById('cuotasTableBody');
    tbody.innerHTML = '';
    
    cuotas.forEach(cuota => {
        const row = document.createElement('tr');
        const badgeClass = getBadgeClass(cuota.estado);
        
        row.innerHTML = `
            <td>${cuota.prestamo}</td>
            <td>${cuota.numero}</td>
            <td>${cuota.vencimiento}</td>
            <td>$${cuota.monto.toLocaleString()}</td>
            <td><span class="badge ${badgeClass}">${getEstadoText(cuota.estado)}</span></td>
            <td>
                <button class="btn btn-sm" onclick="pagarCuota('${cuota.prestamo}', ${cuota.numero})">Pagar</button>
            </td>
        `;
        
        tbody.appendChild(row);
    });
}

function getBadgeClass(estado) {
    switch (estado) {
        case 'pagado': return 'badge-paid';
        case 'pendiente': return 'badge-pending';
        case 'vencido': return 'badge-overdue';
        default: return 'badge-pending';
    }
}

function getEstadoText(estado) {
    switch (estado) {
        case 'pagado': return 'Pagado';
        case 'pendiente': return 'Pendiente';
        case 'vencido': return 'Vencido';
        default: return 'Pendiente';
    }
}

function pagarCuota(prestamo, numero) {
    const cuota = cuotasData.find(c => c.prestamo === prestamo && c.numero === numero);
    
    if (cuota) {
        currentPaymentData = cuota;
        
        // Llenar el modal con los datos de la cuota
        document.getElementById('modalPrestamo').textContent = cuota.prestamo;
        document.getElementById('modalCuota').textContent = cuota.numero;
        document.getElementById('modalMonto').textContent = `$${cuota.monto.toLocaleString()}`;
        document.getElementById('modalVencimiento').textContent = cuota.vencimiento;
        
        // Mostrar el modal
        document.getElementById('paymentModal').style.display = 'flex';
    }
}

function closePaymentModal() {
    document.getElementById('paymentModal').style.display = 'none';
    currentPaymentData = null;
    document.getElementById('cuentaPago').value = '';
}

function confirmarPago() {
    const cuentaPago = document.getElementById('cuentaPago').value;
    
    if (!cuentaPago) {
        alert('Por favor seleccione una cuenta de pago');
        return;
    }
    
    if (currentPaymentData) {
        // Simular el pago
        console.log('Procesando pago:', {
            prestamo: currentPaymentData.prestamo,
            cuota: currentPaymentData.numero,
            monto: currentPaymentData.monto,
            cuentaPago: cuentaPago
        });
        
        // Mostrar mensaje de éxito
        alert('Pago procesado correctamente');
        
        // Cerrar modal
        closePaymentModal();
        
        // Actualizar el estado de la cuota en los datos
        const cuotaIndex = cuotasData.findIndex(c => 
            c.prestamo === currentPaymentData.prestamo && c.numero === currentPaymentData.numero
        );
        
        if (cuotaIndex !== -1) {
            cuotasData[cuotaIndex].estado = 'pagado';
            displayCuotas(cuotasData);
        }
    }
}

// Cerrar modal al hacer clic fuera de él
document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('paymentModal');
    
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            closePaymentModal();
        }
    });
    
    // Mostrar cuotas iniciales
    displayCuotas(cuotasData);
    
    // Botones de pagar cuota
    const pagarButtons = document.querySelectorAll('.btn-pagar');
    pagarButtons.forEach(button => {
        button.addEventListener('click', function() {
            const prestamoId = this.dataset.prestamoId;
            const cuotaId = this.dataset.cuotaId;
            
            // Aquí iría la lógica para procesar el pago
            console.log('Pagar cuota:', cuotaId, 'del préstamo:', prestamoId);
        });
    });
    
    // Botón de pagar todas las cuotas
    const btnPagarTodas = document.getElementById('btnPagarTodas');
    if (btnPagarTodas) {
        btnPagarTodas.addEventListener('click', function() {
            // Aquí iría la lógica para pagar todas las cuotas
            console.log('Pagar todas las cuotas');
        });
    }
}); 