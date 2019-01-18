<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript">
<%@include file="/WEB-INF/jsp/permisos/listar_permisos.js" %>
</script>
<h1>Permisos</h1>

<table>
<tr><th>M&oacute;dulo</th><th>Permiso</th>
<%
// Aca recorro la lista de grupos
%>
<c:forEach items="${groups}" var="group">
<th><c:out value="${group.groupName}"/></th>
</c:forEach>
</tr>
<c:forEach items="${controllers}" var="controllerItem">
	<c:forEach items="${controllerItem.value}" var="metodo">
		<tr>
		<td><c:out value="${controllerItem.key.descripcionClase}"/>
		</td>
		<td><c:out value="${metodo.descripcionPermiso}"/></td>
		<%
		// aca creo casillitas para cada uno de los permisos por grupo.
		// Voy a crear un td donde se muestre una imagen y 
		// al principio todas van a tener el gif animado de la ruedita
		%>
		<c:forEach items="${groups}" var="group">
			<td class="permiso" id="<c:out value="${group.id}"/>-<c:out value="${metodo.nombrePermiso}"/>" onclick="cambiarPermiso(this)">
			<img src="${pageContext.request.contextPath}/img/ui-anim_basic_16x16.gif">
			</td>
		</c:forEach>
		</tr>
	</c:forEach>
</c:forEach>
</table>