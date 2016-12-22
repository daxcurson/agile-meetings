<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Personas</h2>

<p>
<a href="${pageContext.request.contextPath}/personas/add">Agregar nueva persona</a>
</p>

<p>
A continuaci&oacute;n se listan las personas ingresadas en el sistema, e indica
si estos son usuarios del sistema. Para definir grupos y permisos, haga 
click <a href="${pageContext.request.contextPath}/users/index">aqu&iacute;</a>.
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Es usuario?</th>
<th>Habilitada</th>
<th>Acciones</th>
</tr>
<c:forEach items="${personas}" var="persona">
<tr>
<td><c:out value="${persona.id}"/></td>
<td>${persona.nombre}</td>
<td>${persona.user!=null ? "S&iacute;":"No" }</td>
<td>${persona.habilitada==1 ? "S&iacute;":"No"}</td>
<td><a href="${pageContext.request.contextPath}/personas/edit/${persona.id}">Editar</a></td>
</tr>
</c:forEach>
</table>
