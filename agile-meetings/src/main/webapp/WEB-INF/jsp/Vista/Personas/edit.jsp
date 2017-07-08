<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form method="post" commandName="persona">
	<form:input id="PersonaId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_persona"/>
	<input type="submit" name="editar_persona" value="Editar Persona">
</form:form>