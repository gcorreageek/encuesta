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
 			<form action="${pageContext.request.contextPath}/encuesta/encuesta_fin.html" method="post"  >
 			<input type="hidden"  name="idEncuestaAlumno"  value="${idEncuestaAlumno}" >
 			<div id="divPreguntas"  >
 			<c:forEach var="x"  items="${lPregunta}" >
 			<div class="col-md-4">
 					<div class="block-flat"   >
						<div class="header">
							<h3>${x.orden}) ${x.pregunta} (${x.puntaje} puntos)</h3>
						</div>
						<div class="content overflow-hidden">
							<ul class="list-unstyled">
							<c:forEach var="y" items="${x.alternativas}" >
								<li><input type="radio"  name="${x.idPregunta}" value="${y.idAlternativas}"  />   ${y.orden}) ${y.alternativa} (${y.tipoAlternativaD==1?"F":"V"})</li> 
							</c:forEach>
							</ul>							
						</div>
					</div>
			</div>
 			</c:forEach> 
			</div> 
				<div class="form-group" style="text-align: center" >
	              <div class="col-sm-12 col-md-12" style="text-align: center" >
	                <button type="submit" class="btn btn-primary">Terminar Encuesta</button> 
	              </div>
	              </div>
	         </form>       
	                
	                
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