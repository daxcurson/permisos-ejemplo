package ar.com.strellis.permisosejemplo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.strellis.permisosejemplo.model.Group;

public interface GroupDAO extends JpaRepository<Group,Integer>
{
	List<Group> listAllGroups();
	Group findGroupById(long id);
}
