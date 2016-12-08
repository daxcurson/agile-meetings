<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Sprints/fechas_sprint.js" %>
</script>

<fieldset>
<div class="form-group">
<label for="SprintFechaComienzo">Fecha de Comienzo</label>
<form:input path="fecha_comienzo" id="SprintFechaComienzo" class="form-control" />
<form:errors path="fecha_comienzo"/>
</div>
<div class="form-group">
<label for="SprintFechaFin">Fecha de Fin</label>
<form:input path="fecha_fin" id="SprintFechaFin" class="form-control" />
<form:errors path="fecha_fin"/>
</div>
<div class="form-group">
<label for="SprintNombre">Nombre</label>
<form:input path="nombre" id="SprintNombre" class="form-control" />
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="SprintDescripcion">Descripci&oacute;n</label>
<form:textarea cols="80" rows="10" path="descripcion" id="SprintDescripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
<div class="form-group">
<label for="SprintEstado">Estado</label>
<form:select path="estado" id="SprintEstado" class="form-control">
<form:options items="${estados}" itemValue="id" itemLabel="nombre" />
</form:select>
</div>
</fieldset>