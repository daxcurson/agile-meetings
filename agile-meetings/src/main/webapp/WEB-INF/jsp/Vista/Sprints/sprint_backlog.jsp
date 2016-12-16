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

<div id="pickList">
	<div class='row'>
		<div class='col-sm-5'>
			<h2>Product Backlog</h2>
			<select class='form-control pickListSelect pickData' multiple></select>
		</div>
		<div class='col-sm-2 pickListButtons'>
			<button  class='pAdd btn btn-primary btn-sm'>Agregar</button>
			<button  class='pAddAll btn btn-primary btn-sm'>Agregar Todo</button>
			<button  class='pRemove btn btn-primary btn-sm'>Quitar</button>
			<button  class='pRemoveAll btn btn-primary btn-sm'>Quitar todo</button>
		</div>
<form:form method="post" commandName="sprint_list">
		<div class='col-sm-5'>
			<h2>Sprint</h2>
			<form:select path="items" multiple="true" class='form-control pickListSelect pickListResult' />
		</div>
	</div>
</div>
<br><br>
<button class="btn btn-primary" id="asignarItems">Asignar Items</button>
</form:form>