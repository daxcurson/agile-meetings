<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Proyectos</h2>

<p>
<a href="${pageContext.request.contextPath}/proyectos/add">Nuevo Proyecto</a>
</p>

<table>
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Estado</th>
<th>Fecha Comienzo</th>
<th>Fecha Fin</th>
<th>Acciones</th>
</tr>
<c:forEach items="${proyectos}" var="proyecto">
<tr>
<td><c:out value="${proyecto.id}"/></td>
<td>${proyecto.fecha_comienzo}</td>
<td>${proyecto.nombre}</td>
<td>${proyecto.estado.nombre}</td>
<td>${proyecto.fecha_comienzo}</td>
<td>${proyecto.fecha_fin}</td>
<td><a href="${pageContext.request.contextPath}/proyectos/edit/${proyecto.id}">Editar</a> 
<a href="${pageContext.request.contextPath}/backlog/listar/${proyecto.id}">Product Backlog</a> 
<a href="${pageContext.request.contextPath}/sprints/listar/${proyecto.id}">Sprints</a> 
<a href="${pageContext.request.contextPath}/reuniones/listar/${proyecto.id}">Reuniones</a>
</td>
</tr>
</c:forEach>
</table>