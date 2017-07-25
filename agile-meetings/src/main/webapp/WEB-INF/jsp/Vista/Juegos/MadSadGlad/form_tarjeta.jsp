<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<fieldset>
	<div class="form-group">
		<label for="texto">Texto de la tarjeta</label>
		<form:textarea cols="80" rows="10" path="texto" id="TarjetaTexto" class="form-control" />
		<form:errors path="texto"/>
	</div>
	<div class="form-group">
		<label for="estado">Mad, glad, o sad???</label>
		<form:select path="proyecto" id="ReunionProyecto" class="form-control">
		<form:option value="MAD">Mad</form:option>
		<form:option value="SAD">Sad</form:option>
		<form:option value="GLAD">Glad</form:option>
		<form:errors path="proyecto"/>
		</form:select>
		
	</div>
</fieldset>