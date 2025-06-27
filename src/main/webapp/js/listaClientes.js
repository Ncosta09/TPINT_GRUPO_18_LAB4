function searchClients() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const rows = document.querySelectorAll('#clientsTableBody tr');

    rows.forEach(row => {
        const nombre = row.children[1].textContent.toLowerCase();
        const email = row.children[2].textContent.toLowerCase();
        const dni = row.children[0].textContent;

        if (nombre.includes(searchTerm) || email.includes(searchTerm) || dni.includes(searchTerm)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

function filterClients() {
    const status = document.getElementById('statusFilter').value.toLowerCase();
    const tipo = document.getElementById('typeFilter').value.toLowerCase();
    const rows = document.querySelectorAll('#clientsTableBody tr');

    rows.forEach(row => {
        const estadoText = row.children[4].textContent.trim().toLowerCase();
        const tipoText = row.children[5].textContent.trim().toLowerCase();

        const matchEstado = !status || estadoText === status;
        const matchTipo = !tipo || tipoText === tipo;

        row.style.display = (matchEstado && matchTipo) ? '' : 'none';
    });
}

function clearFilters() {
    document.getElementById('searchInput').value = '';
    document.getElementById('statusFilter').value = '';
    document.getElementById('typeFilter').value = '';

    const rows = document.querySelectorAll('#clientsTableBody tr');
    rows.forEach(row => row.style.display = '');
}

function viewClient(dni) {
	//TEMPORAL EN ALERT
    const row = [...document.querySelectorAll('#clientsTableBody tr')]
        .find(r => r.children[0].textContent === dni);

    if (row) {
        const mensaje = `
DNI: ${row.children[0].textContent}
Nombre: ${row.children[1].textContent}
Email: ${row.children[2].textContent}
Teléfono: ${row.children[3].textContent}
Estado: ${row.children[4].textContent.trim()}
Tipo: ${row.children[5].textContent.trim()}
        `;
        alert(mensaje);
    }
}

function cerrarModal() {
    document.getElementById('modalCliente').classList.add('hidden');
}

function editClient(dni) {
    alert("Editar cliente: " + dni);
}

function deleteClient(dni) {
    if (confirm("¿Estás seguro de que querés eliminar al cliente con DNI " + dni + "?")) {
        alert("Cliente eliminado (simulado): " + dni);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('searchInput').addEventListener('input', searchClients);
});
