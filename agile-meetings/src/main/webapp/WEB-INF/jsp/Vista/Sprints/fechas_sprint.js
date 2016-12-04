$(document).ready(function()
{
	$.datepicker.setDefaults({
		showOn: "both",
		buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
		buttonText: "Calendario"
		});
	// Agrego Datepickers a las fechas de comienzo y fin de curso.
	$("#SprintFechaComienzo").datepicker({ dateFormat: "yy-mm-dd" });
	$("#SprintFechaFin").datepicker({ dateFormat: "yy-mm-dd" });
});
