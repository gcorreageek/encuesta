<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:if test="${promedioValido==true}">
<p>El minimo de alumnos a contestado la encuesta y la nota promedio es:</p>
</c:if>
<c:if test="${promedioValido==false}">
<p>El minimo de alumnos no a contestado la encuesta y la nota promedio aun no es valida:</p>
</c:if>
<h3>${notaPromedioXcurso}</h3>