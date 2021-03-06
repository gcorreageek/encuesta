<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
	<div class="page-head">
      <h2>Realizar Encuesta</h2>
      <ol class="breadcrumb">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Encuesta</a></li>
        <li class="active">Realizar Encuesta</li>
      </ol>
    </div>
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
		<div class="block-flat"> 
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	            <c:if test="${totalEncuestaAlumno==0}">
	            <h2>El Alumno no tiene encuestas pendientes</h2>
	            <p>Gracias por usar el sistema de encuestas.</p>
	            </c:if> 
	            <c:if test="${totalEncuestaAlumno>0}">
	             <form action="${pageContext.request.contextPath}/encuesta/encuesta_inicio.html" method="post"  >
	              <div class="form-group"> 
	                <label class="col-sm-3 control-label">Encuesta</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                  <select class="form-control"  id="cboEncuestaAlumno"  name="cboEncuestaAlumno" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                    <c:forEach   var="x" items="${lEncuestaAlumno}"  >
	                    	<option value="${x.idEncuestaAlumno}">
	                    	${x.encuestaprofesor.usuario.apellidoPaterno} ${x.encuestaprofesor.usuario.apellidoMaterno} ${x.encuestaprofesor.usuario.nombre}
	                    	- ${x.encuestaprofesor.encuesta.anio.curso.curso}
	                    	</option> 	
	                    </c:forEach>
	                  </select>									
	                </div>
	              </div> 
	              <div class="form-group" style="text-align: center" >
	              <div class="col-sm-12 col-md-12" style="text-align: center" >
	                <button type="submit" class="btn btn-primary">Siguiente</button> 
	              </div>
	              </div> 
	              </form>
	            </c:if> 
	                 
	            </div> 
	          </div>
 			</div>   
 			<div id="divPreguntas"  >
			</div> 
	               
	                
	                
		</div>	
	</div> 
  </div>
</div>        
<!-- Div que termina todo el contenido -->		
</div> 
<jsp:include page="../plantilla/script_js.jsp"></jsp:include>
<!-- Script --> 
<script type="text/javascript"> 
$(document).ready(function() {
 

	
});
</script>




<jsp:include page="../plantilla/abajo.jsp"></jsp:include>