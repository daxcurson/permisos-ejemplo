package permisos.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import permisos.dao.GroupDAO;
import permisos.model.Group;

@Repository
public class GroupDAOImpl implements GroupDAO
{
	static Logger log = Logger.getLogger(GroupDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> listAllGroups() 
	{
		return sessionFactory.getCurrentSession().createQuery("from Group").list();
	}

	@Override
	public Group findGroupById(long id) 
	{
		return (Group)sessionFactory.getCurrentSession().createQuery("from Group where id="+id).uniqueResult();
	}

	@Override
	public void save(Group g) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(g);
	}
}
