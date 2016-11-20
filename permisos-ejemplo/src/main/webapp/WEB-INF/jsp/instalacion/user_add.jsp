<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>Creaci&oacute;n del usuario administrador</h2>

<p>
Por favor seleccione un nombre de usuario y clave para el usuario administrador
del sistema. Luego podr&aacute; usar este usuario para crear configuraciones adicionales.
</p>

<form method="post" action="${pageContext.request.contextPath}/instalacion/user_add">
            <label for="username">login:</label>
            <input type="text" name="username"/>
            <label for="password">password:</label>
            <input type="password" name="password"/>
            <label for="confirm_password">Confirme password:</label>
            <input type="password" name="confirm_password"/>
        <input type="submit" value="Crear usuario"/>
</form>
