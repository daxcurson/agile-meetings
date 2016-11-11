<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Usuarios</h2>

<p>
<a href="${pageContext.request.contextPath}/users/add">Agregar nuevo usuario</a>
</p>

<p>
A continuaci&oacute;n se listan los usuarios del sistema y a qu&eacute; grupo
pertenecen. Para definir los permisos de los grupos, haga click <a href="${pageContext.request.contextPath}/permisos/listar_permisos">aqu&iacute;</a>.
</p>