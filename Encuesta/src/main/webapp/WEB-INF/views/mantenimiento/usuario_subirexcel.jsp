<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
			<div class="block-flat">
		          <div class="header">							
		            <h3>Subir Excel Alumno</h3>
		          </div>
		          <form:form  method="POST" action="${pageContext.request.contextPath}/usuario/usuario_upload.html"  enctype="multipart/form-data"  >
		          	<input name="file" type="file" />
		          	<input type="submit" value="Subir Excel" >
		          </form:form>
<!-- 		          <div class="content"> -->
<!-- 	                <div class="cl-mcont"> -->
<%-- 				      <form action="http://foxypixel.net/cleanzone/js/dropzone/upload.php" --%>
<%-- 				      class="dropzone" --%>
<%-- 				      id="my-awesome-dropzone"> --%>
				      
<%-- 				      </form> --%>
<!-- 				    </div> -->
<!-- 		          </div> -->
		        </div>
 
		</div>	
	</div> 
  </div>
</div>        
<!-- Div que termina todo el contenido -->		
</div> 
<jsp:include page="../plantilla/script_js.jsp"></jsp:include>
<!-- Script --> 
<jsp:include page="../plantilla/abajo.jsp"></jsp:include>