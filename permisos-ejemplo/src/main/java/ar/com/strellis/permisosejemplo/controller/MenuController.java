package ar.com.strellis.permisosejemplo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.strellis.permisosejemplo.annotations.Descripcion;
import ar.com.strellis.permisosejemplo.annotations.DescripcionClase;

@DescripcionClase(value="Menu principal")
@Controller
@RequestMapping("/")
public class MenuController
{
	@Descripcion(value="Mostrar menu principal",permission="ROLE_MENU_PRINCIPAL")
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_MENU_PRINCIPAL'))")
	@RequestMapping({"/","/index"})
	public ModelAndView mostrarMenu(Model model) 
	{
		ModelAndView modelo=new ModelAndView("menu/index");
		return modelo;
	}
}
