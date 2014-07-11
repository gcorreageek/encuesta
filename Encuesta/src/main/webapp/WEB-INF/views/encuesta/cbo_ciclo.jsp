<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<select class="form-control"  id="cboCiclo">
	<option value="0">Seleccionar</option> 
	<c:forEach var="x" items="${listarCiclo}"  >
	<option value="${x.idDominio}">${x.valor}</option>
	</c:forEach>
</select>	
