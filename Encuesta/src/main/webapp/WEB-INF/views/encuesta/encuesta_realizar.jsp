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
	            <form action="${pageContext.request.contextPath}/encuesta/encuesta_inicio.html" method="post"  >
	              <div class="form-group"> 
	                <label class="col-sm-3 control-label">Encuesta</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                  <select class="form-control"  id="cboEncuestaAlumno"  name="cboEncuestaAlumno" >
	                    <option value="0">Seleccionar</option> 
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
	                <button type="submit" class="btn btn-primary">Comenzar Encuesta</button> 
	              </div>
	              </div>
	              
	              </form>
	                 
	            </div> 
	          </div>
 			</div>   
 			<div id="divPreguntas"  >
 			<div class="col-md-4">
 					<div class="block-flat"   >
						<div class="header">
							<h3>1) ¿Como te llamas?(5 puntos)</h3>
						</div>
						<div class="content overflow-hidden">
							<ul class="list-unstyled">
								<li>a) Cloud Services</li>
								<li>b) Photo Upload</li>
								<li>c) Music Downloads</li>
								<li>d) Notifications</li>
								<li>e) Full Featured UI</li>
							</ul>							
						</div>
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
// 	$("#cboModalidad").change(function () { 
// 		  $("select#cboModalidad option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_modalidad.html",{"":$(this).val()},function(data){
// 			 	$("#divCboModalidad").html(data); 
// 			  }); 
// 	  }); 
// 	}).trigger('change'); 
	setTimeout(function(){ 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cargartodo.html" ,function(data){
		 	$("#divPreguntas").html(data);
		}); 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cbo.html" ,function(data){
		 	$("#divCboPreguntas").html(data);
		}); 
		
	}, 1000);
	
	$("#btnAgregarPregunta").click(function(){
		var txtPregunta = $("#txtPregunta").val();
		var txtPuntaje = $("#txtPuntaje").val(); 
		
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_agregar.html",{
			"txtPregunta":txtPregunta,
			"txtPuntaje":txtPuntaje
		},function(data){
		 	$("#divPreguntas").html(data);
		}); 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cbo.html" ,function(data){
		 	$("#divCboPreguntas").html(data);
		});  
	});
	$("#btnAgregarAltenativa").click(function(){
		var cboPregunta = $("#cboPregunta").val();
		var txtAlternativa = $("#txtAlternativa").val(); 
		var cboOpciones = $("#cboOpciones").val();
		
		$.post("${pageContext.request.contextPath}/encuesta/alternativa_agregar.html",{
			"cboPregunta":cboPregunta,
			"txtAlternativa":txtAlternativa,
			"cboOpciones":cboOpciones
		},function(data){
		 	$("#divPreguntas").html(data);
		});  
	});

	
});
</script>




<jsp:include page="../plantilla/abajo.jsp"></jsp:include>