<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<table class="table no-border hover" >
<thead class="no-border">
<tr >
<th style="width:2%;" >Check</th>
<th style="width:10%;" >Añio-Num</th>
<th style="width:20%;" >Carrera</th>
<th style="width:4%;" >Ciclo</th>
<th style="width:30%;" >Curso</th>
<th style="width:4%;" >Seccion</th>
</tr>
</thead>
<tbody class="no-border-y">
<c:forEach var="x" items="${buscarAnio}"  >
<tr>
<td><input type="checkbox" value="${x.idAnio}" name="checkAnio"  /></td>
<td>${x.ciclo.nombre}</td>
<td>${x.carrera.descripcion}</td>
<td>${x.cicloAcademidoDominio.valor}</td>
<td>${x.curso.curso}</td>
<td>${x.sessionDominio.valor}</td> 
</tr>
</c:forEach>
</tbody>
</table>	
