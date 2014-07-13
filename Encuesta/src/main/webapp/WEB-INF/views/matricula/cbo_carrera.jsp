<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<select class="form-control"  id="cboCarrera">
<!-- 	<option value="0">Seleccionar</option>  -->
	<c:forEach var="x" items="${listarCarrera}"  >
	<option value="${x.idCarrera}">${x.descripcion}</option>
	</c:forEach>
</select>	
