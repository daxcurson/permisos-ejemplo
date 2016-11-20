package permisos.service;

import permisos.model.User;

public interface UserService 
{
	public User getById(long id);
	public void save(User user);
}
