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
	            <c:if test="${totalEncuestaProfesor==0}">
	            <h2>El Profesor no tiene encuestas asignadas</h2>
	            <p>Gracias por usar el sistema de encuestas.</p>
	            </c:if> 
	            <c:if test="${totalEncuestaProfesor>0}"> 
	              <div class="form-group"> 
	                <label class="col-sm-3 control-label">Encuesta</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                  <select class="form-control"  id="cboEncuestaProfesor"  name="cboEncuestaProfesor" >
	                    <c:forEach   var="x" items="${lEncuestaProfesor}"  >
	                    	<option value="${x.idEncuestaProfesor}">
	                    	${x.encuesta.nombreReferente} - ${x.encuesta.anio.curso.curso}
	                    	</option> 	
	                    </c:forEach>
	                  </select>									
	                </div>
	              </div>  
	              <input  type="button"  id="btnGetNota" value="Nota"  >
	              
	            </c:if> 
	                 
	            </div> 
	          </div>
 			</div>   
 			<div id="divNota"  >
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
 $('#btnGetNota').click(function(){
	 var cboEncuestaProfesor = $('#cboEncuestaProfesor').val();
	 $.post("${pageContext.request.contextPath}/encuesta/encuesta_nota_mostrar.html",{
			"cboEncuestaProfesor":cboEncuestaProfesor
		},function(data){
		 	$("#divNota").html(data);
		}); 
	 
 });
});
</script>




<jsp:include page="../plantilla/abajo.jsp"></jsp:include>