<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<div class="form-group">
<label for="BacklogItemTitulo">T&iacute;tulo</label>
<form:input path="titulo" id="BacklogItemTitulo" class="form-control" />
<form:errors path="titulo"/>
</div>
<div class="form-group">
<label for="BacklogItemDescripcion">Descripci&oacute;n</label>
<form:input path="descripcion" id="BacklogItemDescripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
<div class="form-group">
<label for="BacklogItemEstado">Estado</label>
<form:select path="estado" id="BacklogItemEstado" class="form-control">
<form:options items="${estados}" itemValue="id" itemLabel="nombre" />
</form:select>
</div>
</fieldset>