<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
</script>

<h1>Nuevo Item en el Backlog</h1>

<form:form method="post" action="add" commandName="backlog_item">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<tiles:insertAttribute name="form_backlog_item"/>
	<input type="submit" name="agregar_backlog_item" value="Agregar Item al Backlog">
</form:form>