// Obtener elementos del DOM
const burgerMenu = document.getElementById('burgerMenu'); // asumido que está en Header.jsp
const sidebar = document.getElementById('sidebar');
const overlay = document.getElementById('overlay');
const cuentaForm = document.getElementById('cuentaForm');
const cancelBtn = cuentaForm.querySelector('button[type="reset"]'); // botón cancelar
const notification = document.getElementById('notification');


if (burgerMenu) {
    burgerMenu.addEventListener('click', function () {
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');
    });
}


overlay.addEventListener('click', function () {
    sidebar.classList.remove('active');
    overlay.classList.remove('active');
});


cancelBtn.addEventListener('click', function () {
 window.location.href = '#'; //Cambiar al correcto luego
});

// Al enviar el formulario
cuentaForm.addEventListener('submit', function (e) {
	e.preventDefault();
});
