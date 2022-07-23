package ar.com.strellis.permisosejemplo.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User
{
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	@Transient
	private String confirm_password;
	private int enabled;
	
	@OneToOne
	private Group group;
	
	public int getId()
	{
		return id;
	}
	public void setId(int i)
	{
		id=i;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String u)
	{
		username=u;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String p)
	{
		password=p;
	}
	public Group getGroup()
	{
		return this.group;
	}
	public void setGroup(Group s)
	{
		this.group=s;
	}
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
}
