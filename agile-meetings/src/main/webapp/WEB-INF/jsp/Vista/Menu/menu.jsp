<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Men&uacute; principal</h2>

<p>Bienvendo a la aplicaci&oacute;n Agile Meetings. Haga click en alguna de las siguientes opciones para continuar.</p>

<ul>
<li><a href="${pageContext.request.contextPath}/reuniones/index">Reuniones</a></li>
<li><a href="${pageContext.request.contextPath}/configuracion/index">Configuraci&oacute;n</a></li>
</ul>