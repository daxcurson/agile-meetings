<div class="card horizontal">
	<div class="card-image">
		<img src="<% out.print(request.getParameter("imagen")); %>">
	</div>
	<div class="card-stacked">
		<div class="card-content">
			<p>
			<%
			out.println(request.getParameter("texto"));
			%>
			</p>
		</div>
		<div class="card-action">
			<a href="#">This is a link</a>
		</div>
	</div>
</div>