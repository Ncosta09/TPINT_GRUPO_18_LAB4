<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Sexo" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Localidad" %>

<%
  Cliente c = (Cliente) request.getAttribute("cliente");
  List<Sexo> sexos = (List<Sexo>) request.getAttribute("listaSexo");
  List<Nacionalidad> nacs = (List<Nacionalidad>) request.getAttribute("listaNac");
  List<Provincia> provs = (List<Provincia>) request.getAttribute("listaProv");
  List<Localidad> locs = (List<Localidad>) request.getAttribute("listaLoca");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
    <link rel="stylesheet" href="CSS/altaCli.css" type="text/css" />
</head>
<body>
<jsp:include page="/header.jsp" />
  <h2>Editar Cliente</h2>
  <form action="${pageContext.request.contextPath}/ServletModificarCliente" method="post">
    <input type="hidden" name="idCliente" value="<%= c.getIdCliente() %>"></input>

    DNI:
    <input name="dni" value="<%=c.getDni()%>"/><br/>

    CUIL:
    <input name="cuil" value="<%=c.getCuil()%>"/><br/>

    Nombre:
    <input name="nombre" value="<%=c.getNombre()%>"/><br/>

    Apellido:
    <input name="apellido" value="<%=c.getApellido()%>"/><br/>

    Sexo:
    <select name="sexo">
      <% for (Sexo s: sexos) { %>
        <option value="<%=s.getIdSexo()%>"
          <%=s.getIdSexo()==c.getIdSexo()?"selected":""%>>
          <%=s.getDescripcion()%>
        </option>
      <% } %>
    </select><br/>

    Nacionalidad:
    <select name="nacionalidad">
      <% for (Nacionalidad n: nacs) { %>
        <option value="<%=n.getIdNacionalidad()%>"
          <%=n.getIdNacionalidad()==c.getIdNacionalidad()?"selected":""%>>
          <%=n.getNombre()%>
        </option>
      <% } %>
    </select><br/>

    Fecha de nacimiento:
    <input type="date" name="fechaNacimiento"
      value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd")
                   .format(c.getFechaNacimiento()) %>"
    /><br/>

    Dirección:
    <input name="direccion" value="<%=c.getDireccion()%>"/><br/>

    Localidad:
    <select id="localidad" name="localidad">
      <% for (Localidad l: locs) { %>
        <option value="<%=l.getIdLocalidad()%>"
          <%=l.getIdLocalidad()==c.getIdLocalidad()?"selected":""%>
          data-provincia="<%=l.getIdProvincia()%>">
          <%=l.getNombre()%>
        </option>
      <% } %>
    </select><br/>
    
        Provincia:
    <select id="provincia" name="provincia" disabled>
      <% for (Provincia p: provs) { %>
        <option value="<%=p.getIdProvincia()%>"
          <%=p.getIdProvincia()==(c.getIdLocalidad())? "selected":""%>>
          <%=p.getNombre()%>
        </option>
      <% } %>
    </select><br/>
    

    Email:
    <input name="email" value="<%=c.getEmail()%>"/><br/>

    Teléfono:
    <input name="telefono" value="<%=c.getTelefono()%>"/><br/>

    <button type="submit">Guardar cambios</button>
  </form>

  <script>
    // Si quieres que al cargar la página auto-seleccione la provincia:
    const selLoc = document.getElementById('localidad');
    const selProv= document.getElementById('provincia');
    function actualizarProvincia() {
      const opt = selLoc.options[selLoc.selectedIndex];
      selProv.value = opt.getAttribute('data-provincia') || '';
    }
    selLoc.addEventListener('change', actualizarProvincia);
    // Y al inicializar…
    actualizarProvincia();
  </script>
  <script src="js/common.js"></script>
</body>
</html>
