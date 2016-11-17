$(document).ready(function()
{
	$.datepicker.setDefaults({
		showOn: "both",
		buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
		buttonText: "Calendario"
		});
	// Agrego Datepickers a las fechas de comienzo y fin de curso.
	$("#ProyectoFechaComienzo").datepicker({ dateFormat: "yy-mm-dd" });
	$("#ProyectoFechaFin").datepicker({ dateFormat: "yy-mm-dd" });
	$('#BotonAgregarMiembro').click(
			function()
			{
				var url="${pageContext.request.contextPath}/proyectos/agregar_miembro";
				$.getJSON(url,
				{
					persona_id: $("#ProyectoMiembros").val()
				},
				function(miembros)
				{
					if(miembros!==null)
					{
						tabla_miembros(miembros);
					}
				}
				);
			});
});
function tabla_miembros(miembros)
{
	var options="<table>"+
	"<tr><th>Nombre</th><th>Rol</th></tr>";
	$.each(miembros,function(index,miembro)
	{
		options+="<tr>"+
		'<td>'+miembro.persona.nombre+"</td>"+
		'<td>'+"Rol"+"</td>"+
		'</tr>';
	}
	);
	options+="</table>";
	$("#MiembrosProyecto").html(options);
}