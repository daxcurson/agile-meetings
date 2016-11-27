<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Proyectos/buscar_miembros_roles.js" %>
</script>

<h1>Editar Proyecto</h1>

<form:form method="post" commandName="proyecto">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="ProyectoId" type="hidden" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_proyecto"/>
	<input type="submit" name="editar_proyecto" value="Editar Proyecto">
</form:form>