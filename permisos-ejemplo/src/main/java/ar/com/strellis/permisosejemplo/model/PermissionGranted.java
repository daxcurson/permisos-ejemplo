package ar.com.strellis.permisosejemplo.model;

import org.springframework.security.core.GrantedAuthority;

public class PermissionGranted implements GrantedAuthority
{
	private String authority;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PermissionGranted(String authority)
	{
		this.authority=authority;
	}
	@Override
	public String getAuthority() 
	{
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) 
	{
		this.authority = authority;
	}

}
