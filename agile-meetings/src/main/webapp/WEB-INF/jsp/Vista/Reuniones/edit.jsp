<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Editar reuni&oacute;n</h1>

<form:form method="post" action="edit" commandName="reunion">
	<form:input id="ReunionId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_reunion"/>
	<input type="submit" name="editar_reunion" value="Editar Reunion">
</form:form>