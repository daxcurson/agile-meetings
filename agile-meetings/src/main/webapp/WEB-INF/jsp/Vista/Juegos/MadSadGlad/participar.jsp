<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.css"  media="screen,projection"/>
<script src="${pageContext.request.contextPath}/js/materialize.js" type="text/javascript"></script>

<h1>Juego: Mad, Sad, Glad</h1>

<p>Escribir en una tarjeta qu&eacute; cosas te hicieron sentir orgullo (Glad), qu&eacute;
cosas te hicieron sentir triste (Sad) o te enloquecieron (Mad) en este proyecto.</p>

<a href="${pageContext.request.contextPath}/mad_sad_glad/agregar_tarjeta/${juego.id}">Agregar Tarjeta</a>

<div class="container-fluid cards-row">
		<div class="row">
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/img/mad-sad-glad/mad.jpg" height="300" width="300">
			</div>
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/img/mad-sad-glad/sad.jpg" height="300" width="300">
			</div>
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/img/mad-sad-glad/glad.jpg" height="300" width="300">
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
			<%@include file="/WEB-INF/jsp/Vista/Juegos/MadSadGlad/tarjeta_view.jsp" %>
			</div>
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
			</div>
		</div>
</div>