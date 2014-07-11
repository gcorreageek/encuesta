<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<c:forEach var="x" items="${session_preguntas}"  >
<div class="col-md-4">
			<div class="block-flat"   >
			<div class="header">
				<h3>${x.pregunta}(${x.puntaje} puntos)</h3>
				<a href="${pageContext.request.contextPath}/encuesta/pregunta_eliminar.html?id_p=${x.orden}">[x]</a>
			</div>
			<div class="content overflow-hidden">
				<ul class="list-unstyled">
					<c:forEach var="y" items="${x.alternativas}"  >
						<li>${y.alternativa}<a href="${pageContext.request.contextPath}/encuesta/alternativa_eliminar.html?id_p=${x.orden}&id_a=${y.orden}" >[x]</a></li> 
					</c:forEach>
				</ul>							
			</div>
		</div>
</div>
</c:forEach>
 