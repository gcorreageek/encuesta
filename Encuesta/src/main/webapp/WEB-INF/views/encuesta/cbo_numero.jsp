<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<select class="form-control"  id="cboNumero">
	<option value="0">Seleccionar</option> 
	<c:forEach var="x" items="${listarNumero}"  >
	<option value="${x.idDominio}">${x.valor}</option>
	</c:forEach>
</select>	
