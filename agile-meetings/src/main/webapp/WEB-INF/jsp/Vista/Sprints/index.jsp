<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Sprints del proyecto ${proyecto.nombre}</h2>

<p>
<a href="${pageContext.request.contextPath}/sprints/${proyecto.id}/add">Nueva Sprint</a>
</p>

<table>
<tr>
<th>Id</th>
<th>Fecha comienzo</th>
<th>Fecha fin</th>
<th>Nombre</th>
<th>Estado</th>
<th>Acciones</th>
</tr>
<c:forEach items="${sprints}" var="sprint">
<tr>
<td><c:out value="${sprint.id}"/></td>
<td>${sprint.fecha_comienzo}</td>
<td>${sprint.fecha_fin}</td>
<td>${sprint.nombre}</td>
<td>${sprint.estado.nombre}</td>
<td>
<a href="${pageContext.request.contextPath}/sprints/edit/${sprint.id}">Editar</a>
<a href="${pageContext.request.contextPath}/sprints/backlog/${sprint.id}">Backlog del Sprint</a>
</td> 
</tr>
</c:forEach>
</table>
