<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Borrar Sprint</h1>

<p>&iquest;Est&aacute; seguro que desea borrar este Sprint?</p>

<form:form method="post" commandName="sprint">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<tiles:insertAttribute name="view_sprint"/>
	<input type="submit" name="borrar_sprint" value="Borrar Sprint">
</form:form>