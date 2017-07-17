<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Mis reuniones</h1>

<p>Estas son todas las reuniones de las que soy participante.</p>

<table class="table">
<tr><th>Asunto</th><th>Fecha de comienzo</th><th>Fecha de fin</th><th>Proyecto</th><th>Acciones</th></tr>
<c:forEach items="${mis_reuniones}" var="reunion">
	<tr>
	<td>${reunion.asunto}</td>
	<td>${reunion.fecha_comienzo}</td>
	<td>${reunion.fecha_fin}</td>
	<td>${reunion.proyecto.nombre}</td>
	<td><a href="${pageContext.request.contextPath}/reuniones/participar/${reunion.id}">Participar</a></td>
	</tr>
</c:forEach>
</table>