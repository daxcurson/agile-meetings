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
<%
java.util.List<agilemeetings.model.Tarjeta> mad=new java.util.LinkedList<agilemeetings.model.Tarjeta>();
if(pageContext.getRequest().getAttribute("mad") != null)
	mad=(java.util.List<agilemeetings.model.Tarjeta>) request.getAttribute("mad");
java.util.List<agilemeetings.model.Tarjeta> sad=new java.util.LinkedList<agilemeetings.model.Tarjeta>();
if(pageContext.getRequest().getAttribute("sad") != null)
	sad=(java.util.List<agilemeetings.model.Tarjeta>) request.getAttribute("sad");
java.util.List<agilemeetings.model.Tarjeta> glad=new java.util.LinkedList<agilemeetings.model.Tarjeta>();
if(pageContext.getRequest().getAttribute("glad") != null)
	glad=(java.util.List<agilemeetings.model.Tarjeta>) request.getAttribute("glad");
// Ahora construyo iteradores para los 3.
java.util.Iterator<agilemeetings.model.Tarjeta> iteratorMad=mad.iterator();
java.util.Iterator<agilemeetings.model.Tarjeta> iteratorSad=mad.iterator();
java.util.Iterator<agilemeetings.model.Tarjeta> iteratorGlad=mad.iterator();
%>
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
<%
while(iteratorMad.hasNext() || iteratorSad.hasNext() || iteratorGlad.hasNext())
{
%>
		<div class="row">
			<div class="col-md-4">
			<%
			if(iteratorMad.hasNext())
			{
				agilemeetings.model.Tarjeta t=iteratorMad.next();
			%>
			<jsp:include page="/WEB-INF/jsp/Vista/Juegos/MadSadGlad/tarjeta_view.jsp">
				<jsp:param name="texto" value="<%=t.getTexto() %>"/>
			</jsp:include>
			<%
			}
			%>
			</div>
			<div class="col-md-4">
			<%
			if(iteratorSad.hasNext())
			{
				agilemeetings.model.Tarjeta t=iteratorSad.next();
			%>
			<jsp:include page="/WEB-INF/jsp/Vista/Juegos/MadSadGlad/tarjeta_view.jsp">
				<jsp:param name="texto" value="<%=t.getTexto() %>"/>
			</jsp:include>
			<%
			}
			%>
			</div>
			<div class="col-md-4">
			<%
			if(iteratorGlad.hasNext())
			{
				agilemeetings.model.Tarjeta t=iteratorGlad.next();
			%>
			<jsp:include page="/WEB-INF/jsp/Vista/Juegos/MadSadGlad/tarjeta_view.jsp">
				<jsp:param name="texto" value="<%=t.getTexto() %>"/>
			</jsp:include>
			<%
			}
			%>
			</div>
		</div>
<%
}
%>
</div>