<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
	<div class="page-head">
      <h2>Registrar Preguntas</h2>
      <ol class="breadcrumb">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Encuesta</a></li>
        <li class="active">Registrar Preguntas</li>
      </ol>
    </div>
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
		<div class="block-flat"> 
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	            <form action="${pageContext.request.contextPath}/encuesta/asignacionpreguntas_guardar2.html" method="post"  >
	              <span style="color:red" >${mensaje_alert}</span>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Numero Referente</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                   <input type="text" name="numeroReferente"   class="form-control"  />									
	                </div>
	              </div>
	              <div id="divFormulas"  >
	              </div>
	               <div class="form-group">
	                <label class="col-sm-3 control-label">Formula</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                   <input type="text" name="formula"   class="form-control"  />									
	                </div>
	              </div>
	              <div class="form-group" style="text-align: center" >
	              <div class="col-sm-12 col-md-12" style="text-align: center" >
	                <button type="submit" class="btn btn-primary">Guardar</button>
	                <button class="btn btn-default">Cancelar</button>
	              </div>
	              </div>
	              </form>
	              
	              <div class="form-group">
					<label class="col-sm-3 control-label">Pregunta</label>
					<div class="col-sm-6">
						<input class="form-control" type="text" name="pregunta" id="txtPregunta"  >
					</div>
				  </div>
				  <div class="form-group">
					<label class="col-sm-3 control-label">Puntaje</label>
					<div class="col-sm-6">
						<input class="form-control"   type="text" name="puntaje" id="txtPuntaje"  >
					</div>
				  </div>
				  <div class="form-group col-sm-9" style="text-align: right;"> 
				  <input type="button" id="btnAgregarPregunta"  value="Agregar" class="btn btn-primary btn-rad"  /> 
				  </div>    
	            </div>
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	            <div class="content">
	               <div class="form-group">
	                <label class="col-sm-3 control-label">Pregunta</label>
	                <div class="col-sm-6"    >
	                	<div id="divCboPreguntas" >
	                		  <select class="form-control"  id="cboPregunta">
			                    <option value="0">Seleccionar</option>  
			                  </select>	
	                	</div> 			
	                </div>
	              </div>
	              <div class="form-group">
					<label class="col-sm-3 control-label">Alternativa</label>
					<div class="col-sm-6">
						<input class="form-control"   type="text" name="puntaje" id="txtAlternativa"  >
					</div>
				  </div>
				  <div class="form-group">
	                <label class="col-sm-3 control-label">Puntaje</label>
	                <div class="col-sm-6">
						<input class="form-control"   type="text" name="puntajeAlternativa" id="txtPuntajeAlternativa"  >
					</div>
	              </div>
				  <div class="form-group col-sm-9" style="text-align: right;"> 
				  <input type="button" id="btnAgregarAltenativa"  value="Agregar" class="btn btn-primary btn-rad"  /> 
				  </div>
				 </div>
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
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cargarformula.html" ,function(data){
		 	$("#divFormulas").html(data);
		});
		
	}, 1000);
	
	$("#btnAgregarPregunta").click(function(){
		var txtPregunta = $("#txtPregunta").val();
		var txtPuntaje = $("#txtPuntaje").val(); 
		if(txtPregunta==''){
			alert('Ingrese un valor valido en pregunta');
			return;
		} 
		if(txtPuntaje==''){
			alert('Ingrese un valor valido en puntaje');
			return;
		} 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_agregar.html",{
			"txtPregunta":txtPregunta,
			"txtPuntaje":txtPuntaje
		},function(data){
		 	$("#divPreguntas").html(data);
		}); 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cbo.html" ,function(data){
		 	$("#divCboPreguntas").html(data);
		});  
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cargarformula.html" ,function(data){
		 	$("#divFormulas").html(data);
		});
	});
	$("#btnAgregarAltenativa").click(function(){
		var cboPregunta = $("#cboPregunta").val();
		var txtAlternativa = $("#txtAlternativa").val(); 
		var txtPuntajeAlternativa = $("#txtPuntajeAlternativa").val();
		if(cboPregunta==0){
			alert('Seleccione una pregunta');
			return;
		} 
		if(txtAlternativa==''){
			alert('Ingrese un valor valido en alterntiva');
			return;
		}
		if(txtPuntajeAlternativa==''){
			alert('Ingrese un valor valido en puntaje de la alternativa');
			return;
		}
		$.post("${pageContext.request.contextPath}/encuesta/alternativa_agregar.html",{
			"cboPregunta":cboPregunta,
			"txtAlternativa":txtAlternativa,
			"txtPuntajeAlternativa":txtPuntajeAlternativa
		},function(data){
		 	$("#divPreguntas").html(data);
		}); 
		$.post("${pageContext.request.contextPath}/encuesta/pregunta_cargarformula.html" ,function(data){
		 	$("#divFormulas").html(data);
		});
	});

	
});
</script>




<jsp:include page="../plantilla/abajo.jsp"></jsp:include>