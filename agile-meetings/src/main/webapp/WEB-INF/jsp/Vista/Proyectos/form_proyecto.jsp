<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Proyectos/buscar_miembros_roles.js" %>
</script>

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
<c:forEach items="${personas}" var="persona">
<option value="${persona.id}"><c:out value="${persona.nombre}"/></option>
</c:forEach>
</select>
<input type="button" value="Agregar Miembro" id="BotonAgregarMiembro" />
<div id="MiembrosProyecto">
<table>
<tr><th>Nombre</th><th>Rol</th><th>Acciones</th></tr>
<c:choose>
	<c:when test="${not empty proyecto.miembros}">
		<c:forEach items="${proyecto.miembros}" var="rol_persona">
			<tr>
			<td><c:out value="${rol_persona.persona.nombre}" /></td>
			<td><c:out value="${rol_persona.rol.nombre}"/></td>
			<td class="miembro_proyecto" id="<c:out value="${rol_persona.id}"/>">
			<img src="${pageContext.request.contextPath}/img/cross.png" onclick="quitar_miembro(<c:out value="${rol_persona.id}"/>)">
			</td>

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