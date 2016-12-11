<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Borrar Item del backlog</h1>

<p>&iquest;Est&aacute; seguro que desea borrar este elemento del Backlog?</p>

<form:form method="post" commandName="backlog_item">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<tiles:insertAttribute name="view_backlog_item"/>
	<input type="submit" name="borrar_backlog_item" value="Borrar Item">
</form:form>