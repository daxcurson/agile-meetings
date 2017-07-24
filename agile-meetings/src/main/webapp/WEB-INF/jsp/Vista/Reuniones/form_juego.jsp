<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<div class="form-group">
		<label for="tipo_reunion">Tipo de Juego</label>
		<form:select path="tipo_juego" id="JuegoTipoJuego" class="form-control">
		<form:options items="${tipos_juego}" itemValue="id" itemLabel="nombre" />
		</form:select>
		<form:errors path="tipo_juego"/>
	</div>
	<div class="form-group">
		<label for="estado_juego">Estado del juego</label>
		<form:select path="estado_juego" id="JuegoEstadoJuego" class="form-control">
		<form:options items="${estados_juego}" itemValue="id" itemLabel="nombre" />
		</form:select>
		<form:errors path="estado_juego"/>
	</div>
</fieldset>