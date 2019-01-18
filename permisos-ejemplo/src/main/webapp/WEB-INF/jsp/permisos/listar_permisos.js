$(document).ready(function()
{
	BuscarPermisos();
});
/**
 * Busca los permisos de los distintos perfiles.
 * Recorre todos los td de la clase permiso y para cada uno de ellos
 * hace un request ajax donde va y busca que dibujito tiene que mostrar,
 * si el de la X o el del tick.
 * @returns
 */
function BuscarPermisos()
{
	var prefijoPath="${pageContext.request.contextPath}";
	$(".permiso").each( function()
	{
		var permisoBuscar=$(this).attr("id");
		var url=prefijoPath+"/permisos/informar?permiso="+permisoBuscar;
		// Para cada uno, pido un json
		$.getJSON(url,
		{
		},
		function(permiso)
		{
			if(permiso !== null)
			{
				if(permiso)
					$('#'+permisoBuscar).html('<img src="'+prefijoPath+'/img/tick.png">');
				else
					$('#'+permisoBuscar).html('<img src="'+prefijoPath+'/img/cross.png">');
			}
		});
	});
}
/**
 * Recibe sobre que TD se hizo click. Con eso busco el id, y mando un request
 * al sistema para modificar el permiso. 
 * @param item
 * @returns
 */
function cambiarPermiso(item)
{
	var prefijoPath="${pageContext.request.contextPath}";
	var permisoBuscar=item.id;
	var url=prefijoPath+"/permisos/modificar?permiso="+permisoBuscar;
	// Para cada uno, pido un json
	$.getJSON(url,
	{
	},
	function(permiso)
	{
		if(permiso !== null)
		{
			if(permiso)
				$('#'+permisoBuscar).html('<img src="'+prefijoPath+'/img/tick.png">');
			else
				$('#'+permisoBuscar).html('<img src="'+prefijoPath+'/img/cross.png">');
		}
	});
}