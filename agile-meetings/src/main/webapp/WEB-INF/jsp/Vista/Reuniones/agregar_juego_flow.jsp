<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Agregar juego</h1>
<form method="post" action="${flowExecutionUrl}">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<select name="tipo_juego">
<c:forEach items="${tipos_juego}" var="tipo">
<option value="${tipo.id}">${tipo.nombre}</option>
</c:forEach>
</select>
<input type="submit" name="_eventId_continue" value="continue" />
<input type="submit" name="_eventId_cancel" value="cancel" />
</form>