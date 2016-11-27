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
	"<tr><th>Nombre</th><th>Rol</th><th>Acciones</th></tr>";
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
/**
 * Quita a un miembro del proyecto
 * @param id_miembro id del RolPersona que hay que quitar
 * @returns
 */
function quitar_miembro(id_miembro)
{
	var url="${pageContext.request.contextPath}/proyectos/quitar_miembro";
	$.getJSON(url,
	{
		rol_id: id_miembro
	},
	function(miembros)
	{
		if(miembros!==null)
		{
			tabla_miembros(miembros);
		}
	}
	);
}