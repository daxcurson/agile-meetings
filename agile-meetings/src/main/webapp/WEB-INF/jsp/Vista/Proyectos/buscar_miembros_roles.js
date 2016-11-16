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
					user_id: $("#ProyectoMiembros").val()
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
		// El checkbox, si no esta chequeado, ni siquiera es enviado en el request.
		// Como me quiero asegurar que el dato [Curso][ModalidadCurso][modalidad.....] siempre exista,
		// tengo que crear un campo Hidden, como hace el Cakephp, con valor cero.
		options+="<tr>"+
		'<td>'+miembro.nombre+"</td>"+
		'<td>'+"Rol"+"</td>"+
		'</tr>';
	}
	);
	options+="</table>";
	$("#ModalidadesDisponibles").html(options);
}