package permisos.service;

import java.util.List;

import permisos.model.Group;

public interface PermissionService 
{
	List<Group> listAllGroups();
	Group findGroupById(int groupId);
	String grantOrRevokePermission(Group grupo, String permisoBuscar);
}
