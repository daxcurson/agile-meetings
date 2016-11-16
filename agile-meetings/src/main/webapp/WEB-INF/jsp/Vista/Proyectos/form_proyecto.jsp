<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<div class="form-group">
<label for="ProyectoFechaComienzo">Fecha de Comienzo</label>
<form:input path="fecha_comienzo" id="ProyectoFechaComienzo" class="form-control" />
<form:errors path="fecha_comienzo"/>
</div>
<div class="form-group">
<label for="ProyectoFechaFin">Fecha de Fin</label>
<form:input path="fecha_fin" id="ProyectoFechaFin" class="form-control" />
<form:errors path="fecha_fin"/>
</div>
<div class="form-group">
<label for="ProyectoNombre">Nombre</label>
<form:input path="nombre" id="ProyectoNombre" class="form-control" />
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="ProyectoEstado">Estado</label>
<form:select path="estado" id="ProyectoEstado" class="form-control">
<form:options items="${estados}" itemValue="id" itemLabel="nombre" />
</form:select>
</div>
<select name="miembros_proyecto" id="ProyectoMiembros">
<c:forEach items="${users}" var="user">
<option value="${user.id}"><c:out value="${user.nombre}"/></option>
</c:forEach>
</select>
<input type="button" value="Agregar Miembro" id="BotonAgregarMiembro" />
<div id="MiembrosProyecto">
<table>
<tr><th>Nombre</th><th>Rol</th></tr>
<c:choose>
	<c:when test="${not empty miembros}">
		<c:forEach items="${miembros}" var="modalidad">
			<tr>
			<td><c:out value="${user.nombre}" /></td>
			<td>Rol</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="5" style="text-align:center">-- No hay miembros --</td></tr>
	</c:otherwise>
</c:choose>
</table>
</div>
</fieldset>