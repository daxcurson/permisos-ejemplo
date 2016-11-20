package permisos.annotations;

public class NombreClase 
{
	private String nombreClase;
	private String descripcionClase;
	
	public NombreClase()
	{
		
	}
	public NombreClase(String nombreClase,String descripcionClase)
	{
		this.nombreClase=nombreClase;
		this.descripcionClase=descripcionClase;
	}
	
	public void setNombreClase(String n)
	{
		nombreClase=n;
	}
	public String getNombreClase()
	{
		return nombreClase;
	}
	public void setDescripcionClase(String d)
	{
		descripcionClase=d;
	}
	public String getDescripcionClase()
	{
		return descripcionClase;
	}
	public boolean equals(Object otro)
	{
		if(nombreClase==((NombreClase)otro).nombreClase && descripcionClase==((NombreClase)otro).descripcionClase)
			return true;
		else
			return false;
	}
}
