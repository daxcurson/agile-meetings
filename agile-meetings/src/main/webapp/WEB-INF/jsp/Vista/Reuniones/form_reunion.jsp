<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
	<div class="form-group">
		<label for="proyecto">Proyecto</label>
		<form:select path="proyecto" id="ReunionProyecto" class="form-control">
		<form:options items="${proyectos}" itemValue="id" itemLabel="nombre" />
		<form:errors path="proyecto"/>
		</form:select>
	</div>
	<div class="form-group">
		<label for="fecha_comienzo">Fecha de Comienzo</label>
		<form:input path="fecha_comienzo" id="ProyectoFechaComienzo" class="form-control" />
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
</fieldset>