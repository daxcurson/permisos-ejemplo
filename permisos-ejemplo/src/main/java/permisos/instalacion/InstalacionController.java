package permisos.instalacion;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import permisos.controller.AppController;
import permisos.model.User;
import permisos.service.InstalacionService;

@Controller
@RequestMapping("instalacion")
public class InstalacionController extends AppController
{
	static Logger log = Logger.getLogger(InstalacionController.class);
	
	@Autowired
	private InstalacionService instalacionService;

	@RequestMapping({"/","/index"})
	public ModelAndView MostrarMenu()
	{
		return new ModelAndView("instalacion/instalacion_index");
	}
	private ModelAndView cargarFormUsuario(User user)
	{
		ModelAndView modelo=new ModelAndView("instalacion/user_add");
		modelo.addObject(user);
		// Esta pantalla es "seleccione el nombre del usuario administrador",
		// por lo cual vamos a asumir que ese es el grupo del usuario.
		return modelo;
	}
	@RequestMapping(value="/user_add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregar(Model model)
	{
		ModelAndView modelo=this.cargarFormUsuario(new User());
		return modelo;
	}
	@RequestMapping(value = "/user_add", method = RequestMethod.POST)
	public ModelAndView addCurso(@ModelAttribute("user")
	User user, BindingResult result,ModelMap model)
	{
		if(!user.getPassword().equals(user.getConfirm_password()))
			result.addError(new ObjectError("user", "Los passwords no coinciden"));
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormUsuario(user);
			return modelo;
		}
		else
		{
			// El servicio de instalacion ya tiene el metodo necesario
			// para realizar esto.
			instalacionService.grabarUsuarioAdministrador(user);
			model.addAttribute("message","Usuario grabado exitosamente");
			return new ModelAndView("instalacion/instalacion_index");
		}
	}
}
