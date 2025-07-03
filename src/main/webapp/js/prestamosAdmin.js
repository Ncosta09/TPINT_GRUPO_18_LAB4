// Datos de ejemplo para los préstamos

function verPrestamo(boton, id) {
    const fila = boton.closest('tr');
    
    // Obtener datos de la fila
    const cliente = fila.children[1].textContent;
    const importe = fila.children[2].textContent.replace('$','');
    const plazo = fila.children[3].textContent.replace(' meses','');
    const estadoElement = fila.children[4].querySelector('.badge');
    const estado = estadoElement.textContent.trim();
    const estadoClass = estadoElement.className;
    const fecha = fila.children[5].textContent;
    
    // Llenar el modal
    document.getElementById("detalleId").textContent = "PR" + String(id).padStart(3, '0');
    document.getElementById("detalleCliente").textContent = cliente;
    document.getElementById("detalleImporte").textContent = importe;
    document.getElementById("detallePlazo").textContent = plazo;
    
    // Mantener el estilo del badge en el modal
    const estadoSpan = document.getElementById("detalleEstado");
    estadoSpan.innerHTML = `<span class="${estadoClass}">${estado}</span>`;
    
    document.getElementById("detalleFecha").textContent = fecha;
    
    // Mostrar modal con animación
    const modal = document.getElementById("modalPrestamo");
    modal.style.display = 'block';
    
    // Cerrar modal al hacer clic fuera de él
    modal.onclick = function(event) {
        if (event.target === modal) {
            cerrarModal();
        }
    }
}

function cerrarModal() {
    const modal = document.getElementById("modalPrestamo");
    modal.style.display = 'none';
}

// Cerrar modal con tecla Escape
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        cerrarModal();
    }
});

function searchLoans() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    
    let filteredData = loansData;
    
    if (searchTerm) {
        filteredData = loansData.filter(loan => 
            loan.id.toLowerCase().includes(searchTerm) ||
            loan.cliente.toLowerCase().includes(searchTerm) ||
            loan.tipo.toLowerCase().includes(searchTerm)
        );
    }
    
    displayLoans(filteredData);
}

function filterLoans() {
    const statusFilter = document.getElementById('statusFilter').value;
    const typeFilter = document.getElementById('typeFilter').value;
    
    let filteredData = loansData;
    
    if (statusFilter) {
        filteredData = filteredData.filter(loan => loan.estado === statusFilter);
    }
    
    if (typeFilter) {
        filteredData = filteredData.filter(loan => loan.tipo === typeFilter);
    }
    
    displayLoans(filteredData);
}

function clearFilters() {
    document.getElementById('searchInput').value = '';
    document.getElementById('statusFilter').value = '';
    document.getElementById('typeFilter').value = '';
    displayLoans(loansData);
}

function displayLoans(loans) {
    const tbody = document.getElementById('loansTableBody');
    tbody.innerHTML = '';
    
    loans.forEach(loan => {
        const row = document.createElement('tr');
        const badgeClass = getBadgeClass(loan.estado);
        const tipoText = getTipoText(loan.tipo);
        
        row.innerHTML = `
            <td>${loan.id}</td>
            <td>${loan.cliente}</td>
            <td>${tipoText}</td>
            <td>$${loan.monto.toLocaleString()}</td>
            <td>${loan.plazo} meses</td>
            <td><span class="badge ${badgeClass}">${getEstadoText(loan.estado)}</span></td>
            <td>${loan.fechaSolicitud}</td>
            <td class="actions">
                ${getActionButtons(loan)}
            </td>
        `;
        
        tbody.appendChild(row);
    });
}

function getBadgeClass(estado) {
    switch (estado) {
        case 'pendiente': return 'badge-pending';
        case 'aprobado': return 'badge-approved';
        case 'rechazado': return 'badge-rejected';
        case 'activo': return 'badge-active';
        case 'finalizado': return 'badge-finished';
        default: return 'badge-pending';
    }
}

function getEstadoText(estado) {
    switch (estado) {
        case 'pendiente': return 'Pendiente';
        case 'aprobado': return 'Aprobado';
        case 'rechazado': return 'Rechazado';
        case 'activo': return 'Activo';
        case 'finalizado': return 'Finalizado';
        default: return 'Pendiente';
    }
}

function getTipoText(tipo) {
    switch (tipo) {
        case 'personal': return 'Personal';
        case 'hipotecario': return 'Hipotecario';
        case 'automotriz': return 'Automotriz';
        case 'emprendimiento': return 'Emprendimiento';
        default: return tipo;
    }
}

function getActionButtons(loan) {
    let buttons = `<button class="btn btn-sm" onclick="viewLoan('${loan.id}')">Ver</button>`;
    
    if (loan.estado === 'pendiente') {
        buttons += `<button class="btn btn-sm btn-success" onclick="approveLoan('${loan.id}')">Aprobar</button>`;
        buttons += `<button class="btn btn-sm btn-danger" onclick="rejectLoan('${loan.id}')">Rechazar</button>`;
    } else if (loan.estado === 'aprobado' || loan.estado === 'activo') {
        buttons += `<button class="btn btn-sm btn-secondary" onclick="editLoan('${loan.id}')">Editar</button>`;
    }
    
    return buttons;
}

function viewLoan(id) {
    const loan = loansData.find(l => l.id === id);
    if (loan) {
        alert(`Detalles del préstamo:\nID: ${loan.id}\nCliente: ${loan.cliente}\nTipo: ${getTipoText(loan.tipo)}\nMonto: $${loan.monto.toLocaleString()}\nPlazo: ${loan.plazo} meses\nEstado: ${getEstadoText(loan.estado)}\nFecha: ${loan.fechaSolicitud}\nDestino: ${loan.destino}`);
    }
}

function approveLoan(id) {
    const loan = loansData.find(l => l.id === id);
    if (loan) {
        if (confirm(`¿Está seguro de que desea aprobar el préstamo ${id} de ${loan.cliente}?`)) {
            loan.estado = 'aprobado';
            displayLoans(loansData);
            alert('Préstamo aprobado correctamente');
        }
    }
}

function rejectLoan(id) {
    const loan = loansData.find(l => l.id === id);
    if (loan) {
        if (confirm(`¿Está seguro de que desea rechazar el préstamo ${id} de ${loan.cliente}?`)) {
            loan.estado = 'rechazado';
            displayLoans(loansData);
            alert('Préstamo rechazado');
        }
    }
}

function editLoan(id) {
    const loan = loansData.find(l => l.id === id);
    if (loan) {
        // Aquí se podría abrir un modal o redirigir a una página de edición
        alert(`Editando préstamo: ${loan.id}`);
        // window.location.href = `editarPrestamo.jsp?id=${id}`;
    }
}

// Búsqueda en tiempo real
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    
    searchInput.addEventListener('input', function() {
        searchLoans();
    });
    
    // Mostrar préstamos iniciales
    displayLoans(loansData);

    // Botones de acción en la lista de préstamos
    const actionButtons = document.querySelectorAll('.btn-action');
    actionButtons.forEach(button => {
        button.addEventListener('click', function() {
            const action = this.dataset.action;
            const prestamoId = this.dataset.prestamoId;
            
            switch(action) {
                case 'aprobar':
                    if (confirm('¿Está seguro de que desea aprobar este préstamo?')) {
                        console.log('Aprobar préstamo:', prestamoId);
                    }
                    break;
                case 'rechazar':
                    if (confirm('¿Está seguro de que desea rechazar este préstamo?')) {
                        console.log('Rechazar préstamo:', prestamoId);
                    }
                    break;
                case 'ver':
                    console.log('Ver préstamo:', prestamoId);
                    break;
            }
        });
    });
}); 