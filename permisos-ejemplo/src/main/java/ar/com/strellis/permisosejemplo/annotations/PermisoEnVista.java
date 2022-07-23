package ar.com.strellis.permisosejemplo.annotations;

/**
 * Contiene el nombre y la descripcion del permiso que hay que mostrar en la vista,
 * corresponde al permiso obtenido de leer la Annotation en el Controller
 * @author daxcurson
 *
 */
public class PermisoEnVista 
{
	private String nombrePermiso;
	private String descripcionPermiso;
	public String getNombrePermiso() 
	{
		return nombrePermiso;
	}
	public void setNombrePermiso(String nombrePermiso) 
	{
		this.nombrePermiso = nombrePermiso;
	}
	public String getDescripcionPermiso() 
	{
		return descripcionPermiso;
	}
	public void setDescripcionPermiso(String descripcionPermiso) 
	{
		this.descripcionPermiso = descripcionPermiso;
	}
}
