package ar.com.strellis.permisosejemplo.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.strellis.permisosejemplo.dao.GroupDAO;
import ar.com.strellis.permisosejemplo.model.Group;
import ar.com.strellis.permisosejemplo.model.Permission;
import ar.com.strellis.permisosejemplo.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService
{
	@Autowired
	private GroupDAO groupRepository;

	@Override
	@Transactional
	public String grantOrRevokePermission(Group g, String permission) 
	{
		Set<Permission> s=g.getPermissions();
		// El grupo puede no tener ningun permiso, si o si hay que agregar.
		if(s!=null && !s.isEmpty())
		{
			Iterator<Permission> i=s.iterator();
			boolean encontradoPermiso=false;
			Permission pp=null;
			while(i.hasNext() && !encontradoPermiso)
			{
				pp=i.next();
				if(pp.getAuthority().equals(permission))
				{
					encontradoPermiso=true;
				}
			}
			if(encontradoPermiso && pp!=null)
			{
				this.removerPermiso(g, pp);
				return "false";
			}
			else
			{
				this.agregarPermiso(g, permission);
				return "true";
			}
		}
		else
		{
			if(s==null)
				s=new HashSet<Permission>();
			g.setPermissions(s);
			this.agregarPermiso(g, permission);
			return "true";
		}
	}

	private void agregarPermiso(Group g,String permission)
	{
		// Hay que otorgar el permiso!!!
		// Eso es grabando en la base de datos un permiso.
		Permission permisoNuevo=new Permission();
		permisoNuevo.setGroup(g);
		permisoNuevo.setAuthority(permission);
		g.getPermissions().add(permisoNuevo);
		groupRepository.save(g);
	}
	private void removerPermiso(Group g,Permission permission)
	{
		// Si tiene el permiso, lo tengo que sacar.
		g.getPermissions().remove(permission);
		groupRepository.save(g);
	}
	
	@Override
	public List<Group> listAllGroups() 
	{
		return groupRepository.listAllGroups();
	}

	@Override
	public Group findGroupById(int id) 
	{
		return groupRepository.findGroupById(id);
	}
}
