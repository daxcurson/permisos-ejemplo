<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>Introduzca su nombre de usuario y clave / Please enter your username and password</h2>

<form method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
<table>
            <tr><td><label for="j_username">login:</label></td>
            <td><input type="text" name="j_username"/></td></tr>
            <tr><td><label for="j_password">password:</label></td>
            <td><input type="password" name="j_password"/></td></tr>
            <tr><td colspan="2"><label class="forCheckbox" for='_spring_security_remember_me'>
                Remember me:
                <input type='checkbox' name='_spring_security_remember_me'/>
            </label></td></tr>
        <tr><td colspan="2"><input type="submit" value="Login"/></td></tr>
</table>
</form>
