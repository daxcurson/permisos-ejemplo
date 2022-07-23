package ar.com.strellis.permisosejemplo.service;

import ar.com.strellis.permisosejemplo.model.User;

public interface UserService 
{
	public User getById(long id);
	public void save(User user);
}
