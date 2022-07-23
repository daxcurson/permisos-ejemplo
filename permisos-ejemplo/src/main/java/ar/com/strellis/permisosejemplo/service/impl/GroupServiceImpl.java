package ar.com.strellis.permisosejemplo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.strellis.permisosejemplo.dao.GroupDAO;
import ar.com.strellis.permisosejemplo.model.Group;
import ar.com.strellis.permisosejemplo.service.GroupService;


@Service
public class GroupServiceImpl implements GroupService
{
	@Autowired
	private GroupDAO groupDAO;
	@Override
	public Group getById(long id) 
	{
		return groupDAO.findGroupById(id);
	}
	@Override
	public void save(Group group) 
	{
		groupDAO.save(group);
	}
}
