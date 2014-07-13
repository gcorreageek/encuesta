<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
	<div class="page-head">
      <h2>Asignar Alumno</h2>
      <ol class="breadcrumb">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Encuesta</a></li>
        <li class="active">Asignar Alumno</li>
      </ol>
    </div>
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
		<div class="block-flat"> 
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	            <form action="${pageContext.request.contextPath}/encuesta/alumnoasignar_guardar.html" method="post"  >
	              <span style="color: red" >${mensaje_error}</span>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Encuesta</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                  <select class="form-control"  id="cboEncuesta"  name="cboEncuesta" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                    <c:forEach   var="x" items="${lEncuesta}"  >
	                    	<option value="${x.idEncuesta}">${x.nombreReferente}</option> 	
	                    </c:forEach>
	                  </select>									
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Profesor</label>
	                <div class="col-sm-6" id="divCboProfesor"  >
	                  <select class="form-control"  id="cboProfesor1"  name="cboProfesor" >
	                  </select>									
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">N°Alumnos validos</label>
	                <div class="col-sm-6" id="divtxtNumeroAlumnos"  >
	                   	<input class="form-control"   type="text" name="txtNumeroAlumnos" id="txtNumeroAlumnos"  >							
	                </div>
	              </div>
	              <div class="form-group" style="text-align: center" >
	              <div class="col-sm-12 col-md-12" style="text-align: center" >
	                <button type="submit" class="btn btn-primary">Guardar</button>
	                <button class="btn btn-default">Cancelar</button>
	              </div>
	              </div>
	              
	              <div id="divAlumno"  >
	               
	              
				  </div> 
	              
	              
	              </form>
	              
	               
				   
				       
	            </div> 
	          </div>
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
	$("#cboEncuesta").change(function () { 
// 		alert('encuesta!');
		  $("select#cboEncuesta option:selected").each(function () {
			  var cboEncuesta = $("#cboEncuesta").val();
			  if(cboEncuesta==0)
				  return;
			  $.post("${pageContext.request.contextPath}/matricula/profesor_cbo.html",{"cboEncuesta":cboEncuesta},function(data){
			 	$("#divCboProfesor").html(data); 
			  }); 
	  }); 
	}).trigger('change'); 
	$("#cboProfesor2").change(function () {
// 		alert('Profesor 2!!');
	});
	$("#cboProfesor1").change(function () {
// 		alert('Profesor 1!!');
// 		  $("select#cboProfesor option:selected").each(function () {
// 			  var cboProfesor = $("#cboProfesor").val();
// 			  var cboEncuesta = $("#cboEncuesta").val();
// 			  console.log('cbo:'+cboProfesor+'|'+cboEncuesta);
// 			  if(cboProfesor==0)
// 				  return;
// 			  if(cboEncuesta==0)
// 				  return;
// 			  $.post("${pageContext.request.contextPath}/matricula/alumno_listar.html",{"cboEncuesta":cboEncuesta,"cboProfesor":cboProfesor},function(data){
// 			 	$("#divAlumno").html(data); 
// 			  }); 
// 	  }); 
	}).trigger('change'); 
	
// 	setTimeout(function(){ 
// 		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cargartodo.html" ,function(data){
// 		 	$("#divPreguntas").html(data);
// 		}); 
// 		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cbo.html" ,function(data){
// 		 	$("#divCboPreguntas").html(data);
// 		});  
// 	}, 1000);
	
// 	$("#btnAgregarPregunta").click(function(){
// 		var txtPregunta = $("#txtPregunta").val();
// 		var txtPuntaje = $("#txtPuntaje").val();  
// 		$.post("${pageContext.request.contextPath}/encuesta/pregunta_agregar.html",{
// 			"txtPregunta":txtPregunta,
// 			"txtPuntaje":txtPuntaje
// 		},function(data){
// 		 	$("#divPreguntas").html(data);
// 		}); 
// 		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cbo.html" ,function(data){
// 		 	$("#divCboPreguntas").html(data);
// 		});  
// 	});
// 	$("#btnAgregarAltenativa").click(function(){
// 		var cboPregunta = $("#cboPregunta").val();
// 		var txtAlternativa = $("#txtAlternativa").val(); 
// 		var cboOpciones = $("#cboOpciones").val(); 
// 		$.post("${pageContext.request.contextPath}/encuesta/alternativa_agregar.html",{
// 			"cboPregunta":cboPregunta,
// 			"txtAlternativa":txtAlternativa,
// 			"cboOpciones":cboOpciones
// 		},function(data){
// 		 	$("#divPreguntas").html(data);
// 		});  
// 	});

	
});
</script>




<jsp:include page="../plantilla/abajo.jsp"></jsp:include>