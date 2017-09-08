$(document).ready(function()
{
	$.datepicker.setDefaults({
		showOn: "both",
		buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
		buttonText: "Calendario"
		});
	// convierto el selector multiple en un Chosen!
	$("#ReunionParticipantes").chosen(
			{
				no_results_text: "No hay resultados"
			}
			);
	// Agrego Datepickers a las fechas de comienzo y fin de curso.
	$("#ReunionFechaComienzo").datepicker({ dateFormat: "yy-mm-dd" });
	accionBuscarMiembrosProyecto();
	$("#ReunionProyecto").change(function()
	{
		accionBuscarSprints();
	}
	);
	accionBuscarSprints();
});
/**
 * Si se eligio el proyecto, buscar sus miembros, para que aparezcan en un
 * select multiple y se puedan elegir de ahi.
 * @returns
 */
function accionBuscarMiembrosProyecto()
{
	if($("#ReunionProyecto").val().length!=0)
	{
		// Ir al sistema y consultar que miembros tiene este proyecto, para
		// agregarlos en el Select.
		var url="${pageContext.request.contextPath}/proyectos/listar_miembros/"+$("#ReunionProyecto").val();
		$.getJSON(url,
		{
		},
		function(miembros)
		{
			if(miembros!== null)
			{
				populateMiembrosList(miembros);
				$("#ReunionParticipantes").trigger("chosen:updated");
			}
		});
	}
}
/**
 * Busca las sprints de este proyecto
 * @returns
 */
function accionBuscarSprints()
{
	if($("#ReunionProyecto").val().length!=0)
	{
		// Ir al sistema y consultar que miembros tiene este proyecto, para
		// agregarlos en el Select.
		var url="${pageContext.request.contextPath}/sprints/listar_ajax/"+$("#ReunionProyecto").val();
		$.getJSON(url,
		{
		},
		function(sprints)
		{
			if(sprints!== null)
			{
				populateSprintsList(sprints);
			}
		});
	}
}
/**
 * Recibe la lista de miembros del proyecto y los carga en el Select de los miembros,
 * que es un select multiple.
 * @param miembros
 * @returns
 */
function populateMiembrosList(rolPersonas)
{
	var options="";
	$.each(rolPersonas,function(index,rolPersona)
	{
		options+="<option value="+rolPersona.persona.id+">"+rolPersona.persona.nombre+"</option>"
	}
	);
	$("#ReunionParticipantes").html(options);
}
function populateSprintsList(sprints)
{
	var options="";
	$.each(sprints,function(index,sprint)
	{
		options+="<option value="+sprint.id+">"+sprint.descripcion+"</option>"
	}
	);
	$("#ReunionSprint").html(options);
}