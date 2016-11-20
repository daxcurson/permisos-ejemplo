package permisos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import permisos.annotations.Descripcion;
import permisos.annotations.DescripcionClase;

@DescripcionClase(value="Personas")
@Controller
@RequestMapping("personas")
public class PersonasController extends AppController
{
	@RequestMapping({"/","/index"})
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_PERSONAS_MOSTRAR_MENU'))")
	@Descripcion(value="Mostrar menu de personas",permission="ROLE_PERSONAS_MOSTRAR_MENU")
	public ModelAndView MostrarMenu()
	{
		return new ModelAndView("hola_index");
	}
	@Descripcion(value="Borrar persona",permission="ROLE_PERSONAS_BORRAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_BORRAR')")
	@RequestMapping("/personas/delete/{personaId}")
	public ModelAndView deletePersona(@PathVariable("personaId") Integer personaId) 
	{
		return new ModelAndView("personas_borrar");
	}
}
