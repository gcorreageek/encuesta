<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<select class="form-control"  id="cboPregunta">
	<option value="0">Seleccionar</option> 
	<c:forEach var="x" items="${session_preguntas}"  >
	<option value="${x.orden}">${x.pregunta}</option>
	</c:forEach>
</select>	
