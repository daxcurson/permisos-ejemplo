package permisos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import permisos.model.Group;
import permisos.model.User;
import permisos.service.GroupService;
import permisos.service.InstalacionService;
import permisos.service.PermissionService;
import permisos.service.UserService;

@Service
public class InstalacionServiceImpl implements InstalacionService
{
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * Graba el usuario administrador. Crea el grupo administrador por defecto,
	 * y hace al usuario que le proporcionan miembro de ese grupo. Este metodo
	 * es parte del proceso de instalacion del sistema y deberia ejecutarse
	 * una sola vez. Es mas, quiza deba verificar en una tabla que esta
	 * instalacion ya se llevo a cabo y no proceder.
	 */
	@Override
	@Transactional
	public void grabarUsuarioAdministrador(User user) 
	{
		// Los metodos del controller de permisos tienen hard-codeado que el
		// ROLE_ADMIN puede entrar, aparte del rol que le 
		// corresponde a ese controller. Esto se hace asi porque tiene
		// que haber un ROLE_ADMIN que tenga siempre acceso a esas pantallas.
		// Otra manera de hacerlo seria aqui crear un usuario creador
		// de permisos, y asignarle los permisos que cada metodo del controller
		// espera. Queda a criterio del implementador usar el ROLE_ADMIN pero
		// es esencial que exista al menos un rol capaz de entrar a la pantalla
		// de permisos. Si no, tenemos el problema del huevo y la gallina,
		// donde tenemos que dar permisos a los usuarios pero todavia no tenemos
		// un usuario porque para crear uno hay que entrar a la pantalla de
		// permisos!!!
		Group adminGroup=new Group();
		adminGroup.setGroupName("Administradores");
		groupService.save(adminGroup);
		permissionService.grantOrRevokePermission(adminGroup, "ROLE_ADMIN");
		user.setGroup(adminGroup);
		// Aca tenemos que encriptar la password!!!!
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userService.save(user);
	}
}
