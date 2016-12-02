<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Editar Sprint</h1>

<form:form method="post" commandName="sprint">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="SprintId" type="hidden" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_sprint"/>
	<input type="submit" name="editar_sprint" value="Editar Sprint">
</form:form>