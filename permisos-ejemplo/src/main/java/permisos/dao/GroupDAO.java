package permisos.dao;

import java.util.List;
import permisos.model.*;

public interface GroupDAO 
{
	List<Group> listAllGroups();
	Group findGroupById(long id);
	void save(Group g);
}
