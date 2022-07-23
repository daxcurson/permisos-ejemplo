package ar.com.strellis.permisosejemplo.service;

import java.util.List;

import ar.com.strellis.permisosejemplo.model.Group;

public interface PermissionService 
{
	List<Group> listAllGroups();
	Group findGroupById(int groupId);
	String grantOrRevokePermission(Group grupo, String permisoBuscar);
}
