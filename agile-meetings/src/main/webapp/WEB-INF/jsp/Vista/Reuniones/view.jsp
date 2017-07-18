<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Ver Reuni&oacute;n</h1>

<table>
<tr><td><a href="${pageContext.request.contextPath}/juegos/add/${reunion.id}">Agregar juego</a></td></tr>
</table>

<table class="table">
<tr><td>Proyecto</td><td><c:out value="${reunion.proyecto.nombre}"/></td>
</tr>
<tr><td>Participantes</td><td><c:forEach items="${reunion.participantes}" var="participante">
<c:out value="${participante.persona.nombre}"/><br/>
</c:forEach></td></tr>
<tr><td>Tipo de Reuni&oacute;n</td><td><c:out value="${reunion.tipo_reunion.nombre}"/></td></tr>
<tr><td>Fecha</td><td><c:out value="${reunion.fecha_comienzo}"/></td></tr>
<tr><td>Asunto</td><td><c:out value="${reunion.asunto}"/></td></tr>
<tr><td>Resumen de la Reuni&oacute;n</td><td><c:out value="${reunion.resumen}"/></td></tr>
<tr><td>Acciones a realizar luego de la reuni&oacute;n</td><td><c:out value="${reunion.acciones}"/></td></tr>
</table>