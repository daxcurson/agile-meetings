<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<div class="form-group">
<label for="UserUsername">Login</label>
<form:input id="UserUsername" class="form-control" path="username"/>
<form:errors path="username"/>
</div>
<div class="form-group">
<form:label path="group" id="LabelUserGroup">Grupo</form:label>
<form:select path="group" id="UserGroup" class="form-control">
<form:options items="${groups}" itemValue="id" itemLabel="group_name" />
</form:select>
</div>
<form:errors path=""/>
<div class="form-group">
<form:label path="password">Clave</form:label>
<form:input type="password" id="UserPassword" class="form-control" path="password"/>
</div>
<div class="form-group">
<form:label path="confirm_password">Confirmar Clave</form:label>
<form:input type="password" id="UserConfirmPassword" class="form-control" path="confirm_password"/>
</div>
<div class="form-group">
<form:checkbox id="UserEnabled" class="form-control" path="enabled" label="Habilitado"/>
</div>
</fieldset>
