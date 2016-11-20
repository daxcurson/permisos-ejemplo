package permisos.service;

import permisos.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public void save(Group group);
}
