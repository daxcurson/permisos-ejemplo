package permisos.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import permisos.annotations.*;
import permisos.model.Group;
import permisos.model.Permission;
import permisos.service.PermissionService;

@DescripcionClase(value="Permisos")
@Controller
@RequestMapping("permisos")
public class PermisosController extends AppController
{
	static Logger log = Logger.getLogger(PermisosController.class);

	private RequestMappingHandlerMapping handlerMapping;
	@Autowired
	private PermissionService permissionService;
	
	
	@Autowired
	public void setRequestHandlerMapping(RequestMappingHandlerMapping handlerMapping)
	{
		this.handlerMapping=handlerMapping;
	}
	@Descripcion(value="Mostrar menu de permisos",permission="ROLE_PERMISOS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_MOSTRAR_MENU'))")
	@RequestMapping({"/","/index"})
	public ModelAndView mostrarMenu(Model model) 
	{
		ModelAndView modelo=new ModelAndView("permisos/index");
		return modelo;
	}
	@Descripcion(value="Modificar permisos",permission="ROLE_PERMISOS_MODIFICAR")
	@RequestMapping(value="/listar",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_MODIFICAR'))")
	public ModelAndView listarPermisos()
	{
		ModelAndView modelo=new ModelAndView("permisos/listar_permisos");
		// Por reflection, voy a obtener todos los controllers, todos
		// los mappings, y mandarlos a la vista. Despues un javascript
		// va a invocar, para cada uno de los permisos, un metodo para pedir
		// el permiso especifico y poner un grafiquito de tilde o de equis
		Map<NombreClase,List<PermisoEnVista>> controllers=new LinkedHashMap<NombreClase,List<PermisoEnVista>>();
		Map<RequestMappingInfo, HandlerMethod> p=this.handlerMapping.getHandlerMethods();
		for(Map.Entry<RequestMappingInfo, HandlerMethod> entry:p.entrySet())
		{
			// Ahora para cada metodo averiguo el nombre del controller, del 
			// metodo y el tipo de request.
			String controllerName=entry.getValue().getMethod().getDeclaringClass().getName();
			NombreClase n=new NombreClase();
			n.setNombreClase(controllerName);
			if(!controllers.containsKey(n))
			{
				// Si en el mapa Controller ya tengo una clave cuyo nombre
				// es el nombre de esta clase, pido la lista que el mapa controllers
				// tiene y agrego un item.
				List<PermisoEnVista> metodos=new LinkedList<PermisoEnVista>();
				// Pero en vez del nombre del Controller, quiero el valor
				// de la anotacion DescripcionClase.
				DescripcionClase d=entry.getValue().getMethod().getDeclaringClass().getAnnotation(DescripcionClase.class);
				if(d!=null)
				{
					n.setDescripcionClase(d.value());
				}
				else
				{
					n.setDescripcionClase(controllerName);
				}
				controllers.put(n, metodos);
			}
			// Si el metodo que encontre tiene la anotacion @Descripcion,
			// escribo su valor y conservo el nombre del rol.
			Descripcion d=entry.getValue().getMethod().getAnnotation(Descripcion.class);
			if(d!=null)
			{
				PermisoEnVista permiso=new PermisoEnVista();
				permiso.setDescripcionPermiso(d.value());
				permiso.setNombrePermiso(d.permission());
				controllers.get(n).add(permiso);
			}
		}
        modelo.addObject("controllers",controllers);
        // Ahora vamos a buscar los grupos que se hayan creado.
        List<Group> grupos=permissionService.listAllGroups();
        modelo.addObject("groups",grupos);
		return modelo;
	}
	@RequestMapping(value="/informar",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_MODIFICAR'))")
	public @ResponseBody String informarPermiso(@RequestParam("permiso") String permiso)
	{
		// Informa si el permiso que me dan existe.
		// Ahora consulto la base de datos.
		try
		{
			log.trace("Me llamaron con el permiso "+permiso);
			StringTokenizer t=new StringTokenizer(permiso,"-");
			String id=t.nextToken();
			log.trace("Tengo el id? "+id);
			String permisoBuscar=t.nextToken();
			log.trace("Me llamaron, tengo que buscar el permiso "+permisoBuscar+" del grupo "+id);
			// Primero busco el grupo
			Group grupo=permissionService.findGroupById(Integer.parseInt(id));
			if(grupo!=null)
			{
				log.trace("Encontre el grupo, averiguo si tiene el permiso");
				Set<Permission> s=grupo.getPermissions();
				for(Permission pp:s)
				{
					if(pp.getAuthority().equals(permisoBuscar))
						return "true";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "false";
	}
	@RequestMapping(value="/modificar",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERMISOS_MODIFICAR'))")
	public @ResponseBody String modificarPermiso(@RequestParam("permiso") String permiso)
	{
		// Informa si el permiso que me dan existe.
		// Ahora consulto la base de datos.
		try
		{
			StringTokenizer t=new StringTokenizer(permiso,"-");
			String id=t.nextToken();
			String permisoBuscar=t.nextToken();
			Group grupo=permissionService.findGroupById(Integer.parseInt(id));
			if(grupo!=null)
			{
				return permissionService.grantOrRevokePermission(grupo, permisoBuscar);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "error";
	}
}
