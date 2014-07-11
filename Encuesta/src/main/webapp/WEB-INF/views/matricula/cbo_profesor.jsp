<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript"> 
$(document).ready(function() {
	$("#cboProfesor2").change(function () {  
		  $("select#cboProfesor2 option:selected").each(function () {  
			  
			  var cboProfesor = $("#cboProfesor2").val();
			  var cboEncuesta = $("#cboEncuesta").val(); 
			  if(cboProfesor==0)
				  return;
			  if(cboEncuesta==0)
				  return; 
			  $.post("${pageContext.request.contextPath}/matricula/alumno_listar.html",{"cboEncuesta":cboEncuesta,"cboProfesor":cboProfesor},function(data){
			 	$("#divAlumno").html(data); 
			  }); 
	  }); 
	}).trigger('change');	
});
</script>
<select class="form-control"  id="cboProfesor2"   name="cboProfesor" >
	<option value="0">Seleccionar</option> 
	<c:forEach var="x" items="${lProfesor}"  > 
	<option value="${x.usuario.idUsuario}">${x.usuario.apellidoPaterno} ${x.usuario.apellidoMaterno} ${x.usuario.nombre}(${x.usuario.codigo})</option>
	</c:forEach>
</select>	


 