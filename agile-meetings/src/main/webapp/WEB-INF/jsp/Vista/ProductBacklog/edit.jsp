<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
</script>

<h1>Editar Item del Backlog</h1>

<form:form method="post" action="edit" commandName="backlog_item">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="BacklogItemId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_backlog_item"/>
	<input type="submit" name="editar_backlog_item" value="Editar Item del Backlog">
</form:form>