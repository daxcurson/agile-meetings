<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/multiselect.js"></script>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Sprints/asignar_items_del_backlog.js" %>
</script>

<h1>Agregar items del Backlog del proyecto a este Sprint</h1>

<p>De los items de product backlog del proyecto, seleccione aquellos que ser&aacute;n
inclu&iacute;dos en este Sprint.</p>

<div class="row">
	<div class="col-xs-5">
		<h2>Product Backlog</h2>
	</div>
	<div class="col-xs-2">
	</div>
	<div class="col-xs-5">
		<h2>Este Sprint</h2>
	</div>
</div>
<form:form id="form" commandName="sprint">
<div class="row">
    <div class="col-xs-5">
        <select name="from_items" id="multiselect" class="form-control" size="8" multiple="multiple">
        	<c:forEach items="${product_backlog}" var="item">
        		<option value="<c:out value="${item.id}"/>"><c:out value="${item.titulo}"/></option>
        	</c:forEach>
        </select>
    </div>

    <div class="col-xs-2">
        <button type="button" id="multiselect_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
        <button type="button" id="multiselect_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
        <button type="button" id="multiselect_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
        <button type="button" id="multiselect_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
    </div>

    <div class="col-xs-5">
        <select name="to_items" id="multiselect_to" class="form-control" size="8" multiple="multiple">
        	<c:forEach items="${sprint.items}" var="item">
        		<option value="<c:out value="${item.item.id}"/>"><c:out value="${item.item.titulo}"/></option>
        	</c:forEach>
        </select>
    </div>
</div>
<input type="submit" name="submit" value="Modificar Sprint Backlog">
</form:form>