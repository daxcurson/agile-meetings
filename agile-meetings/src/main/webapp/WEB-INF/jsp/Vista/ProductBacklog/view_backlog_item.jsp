<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<table>
<tr><td>T&iacute;tulo</td></tr>
<tr><td>Fecha de creaci&oacute;n</td><td>${backlog_item.fecha_creacion}</td></tr>
<tr><td>Descripci&oacute;n</td><td>${backlog_item.descripcion}</td></tr>
<tr><td>Estado</td><td>${backlog_item.estado.nombre}</td></tr>
</table>