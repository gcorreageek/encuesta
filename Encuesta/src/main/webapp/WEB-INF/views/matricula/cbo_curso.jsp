<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<select class="form-control"  id="cboCurso">
<!-- 	<option value="0">Seleccionar</option>  -->
	<c:forEach var="x" items="${listarCurso}"  >
	<option value="${x.idCurso}">${x.curso}</option>
	</c:forEach>
</select>	
