
        const burgerMenu = document.getElementById('burgerMenu');
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('overlay');
        const clientForm = document.getElementById('clientForm');
        const cancelBtn = document.getElementById('cancelBtn');
        const notification = document.getElementById('notification');
        
        burgerMenu.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });
        
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
        
        cancelBtn.addEventListener('click', function() {
            window.location.href = 'ServletListaClientes';
        });
        
        // Manejo de localidad y provincia
        const selLoc = document.getElementById('localidad');
        const selProv = document.getElementById('provincia');
        
        function actualizarProvincia() {
            const opt = selLoc.options[selLoc.selectedIndex];
            selProv.value = opt.getAttribute('data-provincia') || '';
        }
        
        selLoc.addEventListener('change', actualizarProvincia);
        
        // Inicializar provincia al cargar
        actualizarProvincia();
        
        clientForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Validación básica
            let isValid = true;
            const requiredFields = ['dni', 'cuil', 'nombre', 'apellido', 'sexo', 'nacionalidad', 
                                   'fechaNacimiento', 'direccion', 'localidad', 'email', 'telefono'];
            
            requiredFields.forEach(field => {
                const input = document.getElementById(field);
                const error = document.getElementById(`${field}-error`);
                
                if (!input.value) {
                    if (error) error.style.display = 'block';
                    isValid = false;
                } else {
                    if (error) error.style.display = 'none';
                }
            });
            
            // Validar formato de email
            const email = document.getElementById('email').value;
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            
            if (email && !emailRegex.test(email)) {
                document.getElementById('email-error').style.display = 'block';
                isValid = false;
            }
            
            if (isValid) {
                // Aquí se enviaría el formulario
                console.log('Formulario válido, enviando...');
                // clientForm.submit();
                
                // Mostrar notificación
                notification.style.display = 'block';
                
                
            }
        });