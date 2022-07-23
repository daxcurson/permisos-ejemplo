package ar.com.strellis.permisosejemplo.service;

import ar.com.strellis.permisosejemplo.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public void save(Group group);
}
