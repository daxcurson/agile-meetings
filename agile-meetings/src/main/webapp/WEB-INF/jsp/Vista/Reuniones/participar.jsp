<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_PROYECTOS_MOSTRAR_MENU')">
<%
// Si el administrador de proyecto entra a esta pantalla, ofrecerle agregar un
// juego.
%>
<p>
<a href="${pageContext.request.contextPath}/reuniones/agregar_juego/${reunion.id}">Agregar Juego</a>
</p>
</sec:authorize>

<%
// Listo los juegos que hay en esta reunion e invito a participar en ese.
%>
<table class="table">
<tr><th>Tipo de juego</th><th>Estado</th><th>Acciones</th></tr>
<c:forEach items="${reunion.juegos}" var="juego">
<tr>
<td><c:out value="${juego.tipo_juego.nombre}"/></td>
<td><c:out value="${juego.estado_juego.nombre}"/></td>
<td>
<c:if test="${juego.estado_juego.codigo=='ABIERTO'}">
<sec:authorize access="hasRole('ROLE_JUEGOS_PARTICIPAR')">
<a href="${pageContext.request.contextPath}/juegos/participar/${juego.id}">Participar</a>
</sec:authorize>
</c:if>
</td>
</tr>
</c:forEach>
</table>