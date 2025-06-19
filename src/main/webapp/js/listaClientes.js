// Datos de ejemplo para los clientes
const clientsData = [
    {
        dni: '12345678',
        nombre: 'María González',
        email: 'maria.gonzalez@email.com',
        telefono: '11-1234-5678',
        estado: 'activo',
        tipo: 'cliente'
    },
    {
        dni: '87654321',
        nombre: 'Juan Pérez',
        email: 'juan.perez@email.com',
        telefono: '11-8765-4321',
        estado: 'activo',
        tipo: 'admin'
    },
    {
        dni: '11223344',
        nombre: 'Ana López',
        email: 'ana.lopez@email.com',
        telefono: '11-1122-3344',
        estado: 'inactivo',
        tipo: 'cliente'
    },
    {
        dni: '55667788',
        nombre: 'Carlos Rodríguez',
        email: 'carlos.rodriguez@email.com',
        telefono: '11-5566-7788',
        estado: 'activo',
        tipo: 'cliente'
    },
    {
        dni: '99887766',
        nombre: 'Laura Martínez',
        email: 'laura.martinez@email.com',
        telefono: '11-9988-7766',
        estado: 'suspendido',
        tipo: 'cliente'
    }
];

function searchClients() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    
    let filteredData = clientsData;
    
    if (searchTerm) {
        filteredData = clientsData.filter(client => 
            client.nombre.toLowerCase().includes(searchTerm) ||
            client.email.toLowerCase().includes(searchTerm) ||
            client.dni.includes(searchTerm)
        );
    }
    
    displayClients(filteredData);
}

function filterClients() {
    const statusFilter = document.getElementById('statusFilter').value;
    const typeFilter = document.getElementById('typeFilter').value;
    
    let filteredData = clientsData;
    
    if (statusFilter) {
        filteredData = filteredData.filter(client => client.estado === statusFilter);
    }
    
    if (typeFilter) {
        filteredData = filteredData.filter(client => client.tipo === typeFilter);
    }
    
    displayClients(filteredData);
}

function clearFilters() {
    document.getElementById('searchInput').value = '';
    document.getElementById('statusFilter').value = '';
    document.getElementById('typeFilter').value = '';
    displayClients(clientsData);
}

function displayClients(clients) {
    const tbody = document.getElementById('clientsTableBody');
    tbody.innerHTML = '';
    
    clients.forEach(client => {
        const row = document.createElement('tr');
        const badgeClass = getBadgeClass(client.estado);
        const tipoText = client.tipo === 'admin' ? 'Administrador' : 'Cliente';
        
        row.innerHTML = `
            <td>${client.dni}</td>
            <td>${client.nombre}</td>
            <td>${client.email}</td>
            <td>${client.telefono}</td>
            <td><span class="badge ${badgeClass}">${getEstadoText(client.estado)}</span></td>
            <td>${tipoText}</td>
            <td class="actions">
                <button class="btn btn-sm" onclick="viewClient('${client.dni}')">Ver</button>
                <button class="btn btn-sm btn-secondary" onclick="editClient('${client.dni}')">Editar</button>
                <button class="btn btn-sm btn-danger" onclick="deleteClient('${client.dni}')">Eliminar</button>
            </td>
        `;
        
        tbody.appendChild(row);
    });
}

function getBadgeClass(estado) {
    switch (estado) {
        case 'activo': return 'badge-active';
        case 'inactivo': return 'badge-inactive';
        case 'suspendido': return 'badge-suspended';
        default: return 'badge-inactive';
    }
}

function getEstadoText(estado) {
    switch (estado) {
        case 'activo': return 'Activo';
        case 'inactivo': return 'Inactivo';
        case 'suspendido': return 'Suspendido';
        default: return 'Inactivo';
    }
}

function viewClient(dni) {
    const client = clientsData.find(c => c.dni === dni);
    if (client) {
        alert(`Ver detalles del cliente:\nDNI: ${client.dni}\nNombre: ${client.nombre}\nEmail: ${client.email}\nTeléfono: ${client.telefono}\nEstado: ${getEstadoText(client.estado)}\nTipo: ${client.tipo === 'admin' ? 'Administrador' : 'Cliente'}`);
    }
}

function editClient(dni) {
    const client = clientsData.find(c => c.dni === dni);
    if (client) {
        // Aquí se podría abrir un modal o redirigir a una página de edición
        alert(`Editando cliente: ${client.nombre}`);
        // window.location.href = `editarCliente.jsp?dni=${dni}`;
    }
}

function deleteClient(dni) {
    const client = clientsData.find(c => c.dni === dni);
    if (client) {
        if (confirm(`¿Está seguro de que desea eliminar al cliente ${client.nombre}?`)) {
            // Simular eliminación
            const index = clientsData.findIndex(c => c.dni === dni);
            if (index !== -1) {
                clientsData.splice(index, 1);
                displayClients(clientsData);
                alert('Cliente eliminado correctamente');
            }
        }
    }
}

// Búsqueda en tiempo real
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    
    searchInput.addEventListener('input', function() {
        searchClients();
    });
    
    // Mostrar clientes iniciales
    displayClients(clientsData);
    
    // Botones de acción en la lista de clientes
    const actionButtons = document.querySelectorAll('.btn-action');
    actionButtons.forEach(button => {
        button.addEventListener('click', function() {
            const action = this.dataset.action;
            const clienteId = this.dataset.clienteId;
            
            switch(action) {
                case 'ver':
                    console.log('Ver cliente:', clienteId);
                    break;
                case 'editar':
                    console.log('Editar cliente:', clienteId);
                    break;
                case 'eliminar':
                    if (confirm('¿Está seguro de que desea eliminar este cliente?')) {
                        console.log('Eliminar cliente:', clienteId);
                    }
                    break;
            }
        });
    });
    
    // Botón de nuevo cliente
    const btnNuevoCliente = document.getElementById('btnNuevoCliente');
    if (btnNuevoCliente) {
        btnNuevoCliente.addEventListener('click', function() {
            window.location.href = 'AltaCliente.jsp';
        });
    }
}); 