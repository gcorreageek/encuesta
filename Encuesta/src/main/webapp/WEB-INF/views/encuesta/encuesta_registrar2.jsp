<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
	<div class="page-head">
      <h2>Registrar Encuesta</h2>
      <ol class="breadcrumb">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Encuesta</a></li>
        <li class="active">Registrar</li>
      </ol>
    </div>
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
		<div class="block-flat">
<!--           <div class="header">							 -->
<!--             <h3>Tipo Encuesta</h3> -->
<!--           </div>  -->
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	            <span style="color: red" >${mensaje_error}</span>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Modalidad</label>
	                <div class="col-sm-6" id="divCboModalidad"  >
	                  <select class="form-control"  id="cboModalidad">
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Añio</label>
	                <div class="col-sm-6"  id="divCboAnio"  >
	                  <select class="form-control" id="cboAnio" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Numero</label>
	                <div class="col-sm-6" id="divCboNumero" >
	                  <select class="form-control" id="cboNumero"  >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div> 
<!-- 				  <div class="form-group"> -->
<!-- 					<label class="col-sm-3 control-label">Nombre del Tipo</label> -->
<!-- 					<div class="col-sm-6"> -->
<!-- 						<input class="form-control" type="text"> -->
<!-- 					</div> -->
<!-- 				  </div>  -->
	            </div>
	          </div>
 			</div>
 			<!-- encuesta -->
 			<div class="block-flat">
<!--           <div class="header">							 -->
<!--             <h3>Encuesta</h3> -->
<!--           </div>  -->
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Carrera</label>
	                <div class="col-sm-6" id="divCboCarrera"  >
	                  <select class="form-control" id="cboCarrera" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Ciclo</label>
	                <div class="col-sm-6" id="divCboCiclo" >
	                  <select class="form-control" id="cboCiclo" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div> 
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Curso</label>
	                <div class="col-sm-6" id="divCboCurso" >
	                  <select class="form-control" id="cboCurso" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div> 
	              <div class="form-group">
	                <label class="col-sm-3 control-label">Session</label>
	                <div class="col-sm-6" id="divCboSeccion" >
	                  <select class="form-control" id="cboSeccion" >
<!-- 	                    <option value="0">Seleccionar</option>  -->
	                  </select>									
	                </div>
	              </div>
				<div class="form-group"  style="text-align: right;padding-right: 270px;">
<!-- 					<div class="col-sm-offset-3 "   style="text-align: center;" > -->
<!-- 						<button type="submit" class="btn btn-primary">Buscar</button> -->
						<button class="btn btn-primary"  id="btnBuscar" >Buscar</button>
<!-- 					</div> -->
				</div>

	            </div>
	          </div>
 			</div>
 			<form action="${pageContext.request.contextPath}/encuesta/encuesta_registrarGuardar2.html" method="post" >
 			<div id="divTablaAnio"  class="table-responsive" >
 			</div> 
 			<div class="block-flat"> 
			<div class="content">
	            <div class="form-horizontal group-border-dashed"   style="border-radius: 0px;">
	              <div class="form-group">
					<label class="col-sm-3 control-label">Numero Referente</label>
					<div class="col-sm-6" id="divCboNumeroReferente"  >
	                  <select class="form-control"  id="cboNumeroReferente"  name="cboNumeroReferente" >
	                    <c:forEach   var="x" items="${lNumeroEncuesta}"  >
	                    	<option value="${x.idNumeroEncuesta}">${x.numeroReferente}</option> 	
	                    </c:forEach>
	                  </select>									
	                </div>
				  </div>
				  <input type="submit"  value="Registrar Encuesta"/> 
	            </div>
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
// 	alert('here jquery!');
	$.post("${pageContext.request.contextPath}/encuesta/load_modalidad.html",{"":$(this).val()},function(data){
			 	$("#divCboModalidad").html(data); 
			  }); 
	$.post("${pageContext.request.contextPath}/encuesta/load_anio.html",{"":$(this).val()},function(data){
	 	$("#divCboAnio").html(data);
	  }); 
	 $.post("${pageContext.request.contextPath}/encuesta/load_numero.html",{"":$(this).val()},function(data){
		 	$("#divCboNumero").html(data);
		  }); 
	 $.post("${pageContext.request.contextPath}/matricula/load_carrera.html",{"":$(this).val()},function(data){
		 	$("#divCboCarrera").html(data);
		  }); 
	 $.post("${pageContext.request.contextPath}/encuesta/load_ciclo.html",{"":$(this).val()},function(data){
		 	$("#divCboCiclo").html(data);
		  }); 
$.post("${pageContext.request.contextPath}/matricula/load_curso.html",{"":$(this).val()},function(data){
	$("#divCboCurso").html(data);
});
$.post("${pageContext.request.contextPath}/encuesta/load_seccion.html",{"":$(this).val()},function(data){
	$("#divCboSeccion").html(data);
}); 
// 	$("#cboModalidad").change(function () { 
// 		  $("select#cboModalidad option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_modalidad.html",{"":$(this).val()},function(data){
// 			 	$("#divCboModalidad").html(data); 
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
// 	$("#cboAnio").change(function () { 
// 		  $("select#cboAnio option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_anio.html",{"":$(this).val()},function(data){
// 			 	$("#divCboAnio").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
// 	$("#cboNumero").change(function () { 
// 		  $("select#cboNumero option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_numero.html",{"":$(this).val()},function(data){
// 			 	$("#divCboNumero").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
// 	$("#cboCarrera").change(function () { 
// 		  $("select#cboCarrera option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/matricula/load_carrera.html",{"":$(this).val()},function(data){
// 			 	$("#divCboCarrera").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
// 	divCboCiclo
// 	$("#cboCiclo").change(function () { 
// 		  $("select#cboCiclo option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_ciclo.html",{"":$(this).val()},function(data){
// 			 	$("#divCboCiclo").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
// 	$("#cboCurso").change(function () { 
// 		  $("select#cboCurso option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/matricula/load_curso.html",{"":$(this).val()},function(data){
// 			 	$("#divCboCurso").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
// 	$("#cboSeccion").change(function () { 
// 		  $("select#cboSeccion option:selected").each(function () {
// 			  $.post("${pageContext.request.contextPath}/encuesta/load_seccion.html",{"":$(this).val()},function(data){
// 			 	$("#divCboSeccion").html(data);
// 			  }); 
// 	  }); 
// 	}).trigger('change');
	
	$("#btnBuscar").click(function(){
		var cboModalidadValor = $("#cboModalidad").val();
		var cboAnioValor = $("#cboAnio").val();
		var cboNumeroValor = $("#cboNumero").val();
		var cboCarreraValor = $("#cboCarrera").val();
		var cboCicloValor = $("#cboCiclo").val();
		var cboCursoValor = $("#cboCurso").val();
		var cboSeccionValor = $("#cboSeccion").val(); 
		
		$.post("${pageContext.request.contextPath}/matricula/anio_buscar.html",{
			"idModalidadD":cboModalidadValor,
			"idAnioD":cboAnioValor,
			"idNumeroD":cboNumeroValor,
			"idCarrera":cboCarreraValor,
			"idCicloD":cboCicloValor,
			"idCurso":cboCursoValor,
			"idSeccionD":cboSeccionValor
		},function(data){
		 	$("#divTablaAnio").html(data);
		}); 
		
		
		
		
	});

	
});
</script>
<jsp:include page="../plantilla/abajo.jsp"></jsp:include>