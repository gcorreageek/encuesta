<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<table class="table no-border hover" >
<thead class="no-border">
<tr >
<th style="width:2%;" >Check</th>
<th style="width:10%;" >Codigo</th>
<th style="width:20%;" >Alumno</th>
<th style="width:4%;" >Email</th> 
</tr>
</thead>
<tbody class="no-border-y">
<c:forEach var="x" items="${lMatricula}"  >
<tr> 
<td><input type="checkbox" value="${x.usuario.idUsuario}" name="checkAlumno"  /></td>
<td>${x.usuario.codigo}</td>
<td>${x.usuario.apellidoPaterno} ${x.usuario.apellidoMaterno} ${x.usuario.nombre}</td>
<td>${x.usuario.email}</td> 
</tr>
</c:forEach>
</tbody>
</table>	
