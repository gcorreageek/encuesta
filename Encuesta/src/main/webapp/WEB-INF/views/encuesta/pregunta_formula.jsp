<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
Variables para hacer la formula del resultado:
<c:forEach var="x" items="${session_preguntas}"  >
 <i style="color: blue" >P${x.orden}</i>=<i style="color:aqua;" >${x.puntaje}</i>,  
<c:forEach var="y" items="${x.alternativas}"  >
	  <i style="color: blue" >P${x.orden}_${y.orden}</i>=<i  style="color:aqua;" >${y.puntaje}</i>, 
</c:forEach> 
</c:forEach>
 