document.addEventListener('DOMContentLoaded', function() {
    const notification = document.getElementById('notification');
    
    // Función para mostrar notificaciones
    function showNotification(message, isError = false) {
        notification.textContent = message;
        notification.className = isError ? 'notification error' : 'notification';
        notification.style.display = 'block';
        
        setTimeout(() => {
            notification.style.display = 'none';
        }, 3000);
    }
    
    // Manejo de búsqueda en tiempo real
    document.getElementById('searchInput').addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        const rows = document.querySelectorAll('#cuentasTableBody tr');
        
        rows.forEach(row => {
            const cliente = row.cells[0].textContent.toLowerCase();
            
            if (searchTerm === '' || cliente.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
    
    // Manejo de filtros ambos
    document.getElementById('filterBtn').addEventListener('click', function() {
        const tipoFilter = document.getElementById('tipoFilter').value;
        const estadoFilter = document.getElementById('estadoFilter').value;
        const rows = document.querySelectorAll('#cuentasTableBody tr');
        
        rows.forEach(row => {
			const tipo = row.getAttribute('data-tipo').toLowerCase();
			const estado = row.getAttribute('data-estado').toLowerCase();
            
            let showRow = true;
            
            if (tipoFilter && tipo !== tipoFilter) {
                showRow = false;
            }
            
            if (estadoFilter && estado !== estadoFilter) {
                showRow = false;
            }
            
            row.style.display = showRow ? '' : 'none';
        });
    });
    
    // Sidebar toggle
    const burgerMenu = document.getElementById('burgerMenu');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');
    
    if (burgerMenu) {
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });
    }
    
    if (overlay) {
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
    }
});