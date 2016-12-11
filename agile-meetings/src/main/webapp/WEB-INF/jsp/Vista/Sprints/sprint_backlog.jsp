<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pickList.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pickList.js"></script>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Sprints/asignar_items_del_backlog.js" %>
</script>

<h1>Agregar items del Backlog del proyecto a este Sprint</h1>

<p>De los items de product backlog del proyecto, seleccione aquellos que ser&aacute;n
inclu&iacute;dos en este Sprint.</p>

<div id="pickList"></div>
<br><br>
<button class="btn btn-primary" id="asignarItems">Asignar Items</button>
