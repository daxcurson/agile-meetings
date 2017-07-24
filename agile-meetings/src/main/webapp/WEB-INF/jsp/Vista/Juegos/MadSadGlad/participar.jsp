<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.css"  media="screen,projection"/>
<script src="${pageContext.request.contextPath}/js/materialize.js" type="text/javascript"></script>

<div class="container-fluid cards-row">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="card horizontal">
					<div class="card-image">
						<img src="https://lorempixel.com/100/190/nature/6">
					</div>
					<div class="card-stacked">
						<div class="card-content">
							<p>I am a very simple card. I am good at containing small bits of information.</p>
						</div>
						<div class="card-action">
							<a href="#">This is a link</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="card horizontal">
					<div class="card-image">
						<img src="https://lorempixel.com/100/190/nature/6">
					</div>
					<div class="card-stacked">
						<div class="card-content">
							<p>I am a very simple card. I am good at containing small bits of information.</p>
						</div>
						<div class="card-action">
							<a href="#">This is a link</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="card horizontal">
					<div class="card-image">
						<img src="https://lorempixel.com/100/190/nature/6">
					</div>
					<div class="card-stacked">
						<div class="card-content">
							<p>I am a very simple card. I am good at containing small bits of information.</p>
						</div>
						<div class="card-action">
							<a href="#">This is a link</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>