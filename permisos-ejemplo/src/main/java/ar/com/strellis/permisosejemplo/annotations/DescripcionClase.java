package ar.com.strellis.permisosejemplo.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DescripcionClase 
{
	String value() default "Clase no especificada";
}
