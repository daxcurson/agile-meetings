<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<table>
<tr><td>T&iacute;tulo</td></tr>
<tr><td>Fecha de comienzo</td><td>${sprint.fecha_comienzo}</td></tr>
<tr><td>Fecha de fin</td><td>${sprint.fecha_fin}</td></tr>
<tr><td>Nombre</td><td>${sprint.nombre}</td></tr>
<tr><td>Descripci&oacute;n</td><td>${sprint.descripcion}</td></tr>
<tr><td>Estado</td><td>${sprint.estado.nombre}</td></tr>
</table>