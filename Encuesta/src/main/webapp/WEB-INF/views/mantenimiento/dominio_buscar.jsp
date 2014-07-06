<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="../plantilla/arriba.jsp"></jsp:include>
<div class="container-fluid" id="pcont">
  <div class="cl-mcont">   
	<div class="row dash-cols">
		<div class="col-sm-12 col-md-12">
<form:form action="inicio_dominio_buscar.html" commandName="dominio" method="post" >
<label>Campo:</label>
<input type="text" name="campo" value="${dominio.campo}" />
<input type="submit" value="Buscar" />
</form:form>
<table>
<tr>
<td>ID</td><td>CAMPO</td><td>VALOR</td><td>VALOR_CORTO</td><td>DATOS</td><td>IDHIJO</td><td>IDSUB</td><td>OBLI</td><td>HAB</td>
<td>OPC</td>
</tr>
 <c:forEach var="x"  items="${lstDominio}" >
 <tr>
 	<td>${x.idDominio}</td>
	<td>${x.campo}</td>
	<td>${x.valor}</td>
	<td>${x.valorCorto}</td>
	<td>${x.masDatos}</td>
	<td>${x.dominio1.idDominio}</td>
	<td>${x.dominio2.idDominio}</td>
	<td>${x.obligatorio}</td>
	<td><a href="dominio_eliminar.html?idDominio=${x.idDominio}&campo=${dominio.campo}" >${x.habilitado}</a></td> 
	<td><a href="dominio_modificar.html?idDominio=${x.idDominio}&campo=${dominio.campo}" >[M]</a></td> 	
 </tr>
 </c:forEach>
 </table>
 <br>
 <form:form action="inicio_dominio_nuevo.html" >
 <input type="submit" value="Nuevo"  />
 </form:form>
		</div>	
	</div> 
  </div>
</div>        
<!-- Div que termina todo el contenido -->		
</div> 
<jsp:include page="../plantilla/script_js.jsp"></jsp:include>
<!-- Script --> 
<jsp:include page="../plantilla/abajo.jsp"></jsp:include>