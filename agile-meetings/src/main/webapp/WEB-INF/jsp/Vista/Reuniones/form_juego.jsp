<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fieldset>
	<div class="form-group">
		<label for="tipo_reunion">Tipo de Juego</label>
		<select name="tipo_juego" id="JuegoTipoJuego" class="form-control">
		<c:forEach items="${tipos_juego}" var="tipo_juego">
		<option value="${tipo_juego.id}">${tipo_juego.nombre}</option>
		</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="estado_juego">Estado del juego</label>
		<select name="estado_juego" id="JuegoEstadoJuego" class="form-control">
		<c:forEach items="${estados_juego}" var="estado_juego">
		<option value="${estado_juego.id}">${estado_juego.nombre}</option>
		</c:forEach>
		</select>
	</div>
</fieldset>