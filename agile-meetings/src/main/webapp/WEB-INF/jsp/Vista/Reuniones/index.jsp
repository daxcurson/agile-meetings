<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Reuniones</h2>

<p>
<a href="${pageContext.request.contextPath}/reuniones/add">Nueva Reuni&oacute;n</a>
</p>

<table>
<tr>
<th>Id</th>
<th>Fecha</th>
<th>Asunto</th>
<th>Proyecto</th>
<th>Acciones</th>
</tr>
<c:forEach items="${reuniones}" var="reunion">
<tr>
<td><c:out value="${reunion.id}"/></td>
<td>${reunion.fecha_comienzo}</td>
<td>${reunion.asunto}</td>
<td>${reunion.proyecto.nombre}</td>
<td><a href="${pageContext.request.contextPath}/reuniones/edit/${reunion.id}">Editar</a></td>
</tr>
</c:forEach>
</table>
