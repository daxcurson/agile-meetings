<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Reuniones/buscar_miembros_proyecto.js" %>
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chosen.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chosen.jquery.js"></script>

<fieldset>
	<div class="form-group">
		<label for="proyecto">Proyecto</label>
		<form:select path="proyecto" id="ReunionProyecto" class="form-control">
		<form:options items="${proyectos}" itemValue="id" itemLabel="nombre" />
		<form:errors path="proyecto"/>
		</form:select>
	</div>
	<div class="form-group">
		<label for="tipo_reunion">Tipo de Reuni&oacute;n</label>
		<form:select path="tipo_reunion" id="ReunionTipoReunion" class="form-control">
		<form:options items="${tipos_reunion}" itemValue="id" itemLabel="nombre" />
		</form:select>
		<form:errors path="tipo_reunion"/>
	</div>
	<div class="form-group">
		<label for="participantes">Participantes</label>
		<form:select data-placeholder="Elija miembros..." path="participantes" id="ReunionParticipantes" class="form-control" multiple="true">
		</form:select>
		<form:errors path="participantes"/>
	</div>	
	<div class="form-group">
		<label for="fecha_comienzo">Fecha de Reuni&oacute;n</label>
		<form:input path="fecha_comienzo" id="ReunionFechaComienzo" class="form-control" />
		<form:errors path="fecha_comienzo"/>
	</div>
	<div class="form-group">
		<label for="asunto">Asunto</label>
		<form:textarea cols="80" rows="10" path="asunto" id="ReunionAsunto" class="form-control" />
		<form:errors path="asunto"/>
	</div>
	<div class="form-group">
		<label for="resumen">Resumen de la Reuni&oacute;n</label>
		<form:textarea cols="80" rows="10" path="resumen" id="ReunionResumen" class="form-control" />
		<form:errors path="resumen"/>
	</div>
	<div class="form-group">
		<label for="acciones">Acciones a realizar luego de la reuni&oacute;n</label>
		<form:textarea cols="80" rows="10" path="acciones" id="ReunionAcciones" class="form-control" />
		<form:errors path="resumen" />
	</div>
</fieldset>