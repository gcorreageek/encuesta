package com.encuesta.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
//import javax.el.*;




import javax.el.ELProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encuesta.model.Alternativa;
import com.encuesta.model.Dominio;
import com.encuesta.model.Encuesta;
import com.encuesta.model.EncuestaAlumno;
import com.encuesta.model.EncuestaProfesor;
import com.encuesta.model.Marcada;
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.Usuario;
import com.encuesta.service.DominioService;
import com.encuesta.service.EncuestaService;
import com.encuesta.service.MatriculaService;
import com.encuesta.service.UsuarioService;
import com.encuesta.util.Utiles;
@RequestMapping("/encuesta")
@Controller
public class EncuestaController {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	MatriculaService matriculaService;
	@Autowired
	EncuestaService encuestaService;
	@Autowired
	DominioService dominioService;
 
	
	@RequestMapping("/ir.html")
	public String ir(HttpServletRequest request,Model m){ 
		return "/encuesta/encuesta_registrar";
	}
	@RequestMapping("/encuesta_registrar2.html")
	public String encuestaRegistrar2(HttpServletRequest request,Model m){ 
		List<NumeroEncuesta> lNumeroEncuesta = encuestaService.listarNumeroEncuesta();
		for (NumeroEncuesta ne : lNumeroEncuesta) {
			log.debug("ne:"+ne.getIdNumeroEncuesta());
		}
		request.setAttribute("lNumeroEncuesta", lNumeroEncuesta);
		return "/encuesta/encuesta_registrar2";
	}
//	load_modalidad.html
	@RequestMapping("/load_modalidad.html")
	public String cargarModalidad(HttpServletRequest request,Model m){ 
		List<Dominio> lDominio = null;
		try {
			lDominio = dominioService.listarModalidad();
			for (Dominio x : lDominio) {
				log.debug("Dominio:"+x.getIdDominio()+"|"+x.getValor());
			}
		} catch (Exception e) {
			log.error("[cargarModalidad]",e);
		}
		m.addAttribute("listarModalidad", lDominio);
		return "/encuesta/cbo_modalidad";
	}
//	load_anio
	@RequestMapping("/load_anio.html")
	public String cargarAnio(HttpServletRequest request,Model m){ 
		List<Dominio> lDominio = null;
		try {
			lDominio = dominioService.listarAnio();
			for (Dominio x : lDominio) {
				log.debug("Dominio:"+x.getIdDominio()+"|"+x.getValor());
			}
		} catch (Exception e) {
			log.error("[cargarAnio]",e);
		}
		m.addAttribute("listarAnio", lDominio);
		return "/encuesta/cbo_anio";
	}
	@RequestMapping("/load_numero.html")
	public String cargarNumero(HttpServletRequest request,Model m){ 
		List<Dominio> lDominio = null;
		try {
			lDominio = dominioService.listarNumero();
			for (Dominio x : lDominio) {
				log.debug("Dominio:"+x.getIdDominio()+"|"+x.getValor());
			}
		} catch (Exception e) {
			log.error("[cargarNumero]",e);
		}
		m.addAttribute("listarNumero", lDominio);
		return "/encuesta/cbo_numero";
	} 
	@RequestMapping("/load_ciclo.html")
	public String cargarCiclo(HttpServletRequest request,Model m){ 
		List<Dominio> lDominio = null;
		try {
			lDominio = dominioService.listarCiclo();
			for (Dominio x : lDominio) {
				log.debug("Dominio:"+x.getIdDominio()+"|"+x.getValor());
			}
		} catch (Exception e) {
			log.error("[cargarCiclo]",e);
		}
		m.addAttribute("listarCiclo", lDominio);
		return "/encuesta/cbo_ciclo";
	}
	
	@RequestMapping("/load_seccion.html")
	public String cargarSeccion(HttpServletRequest request,Model m){ 
		List<Dominio> lDominio = null;
		try {
			lDominio = dominioService.listarSeccion();
			for (Dominio x : lDominio) {
				log.debug("Dominio:"+x.getIdDominio()+"|"+x.getValor());
			}
		} catch (Exception e) {
			log.error("[cargarSeccion]",e);
		}
		m.addAttribute("listarSeccion", lDominio);
		return "/encuesta/cbo_seccion";
	}
	
	@RequestMapping("/encuesta_registrar.html")
	public String encuestaRegistrar(HttpServletRequest request,Model m){ 
		String[] idAnios =request.getParameterValues("checkAnio");
		String nombreReferente = request.getParameter("nombreReferente");
		try {
				encuestaService.registrarEncuesta(idAnios, nombreReferente);
		} catch (Exception e) {
			log.error("[encuestaRegistrar]",e);
		}
		return ir(request,m);
	}
	@RequestMapping("/encuesta_registrarGuardar2.html")
	public String encuestaRegistrarGuardar2(HttpServletRequest request,Model m){ 
		String[] idAnios =request.getParameterValues("checkAnio");
		String cboNumeroReferente = request.getParameter("cboNumeroReferente");
		if(idAnios==null || idAnios.length==0){
			request.setAttribute("mensaje_error", "Busque un Año academico");
			return encuestaRegistrar2(request,m);
		}
		try {
			NumeroEncuesta ne = new NumeroEncuesta();
			ne.setIdNumeroEncuesta(Integer.parseInt(cboNumeroReferente));
			encuestaService.registrarEncuesta(idAnios, ne); 
		} catch (Exception e) {
			log.error("[encuestaRegistrar]",e);
		}
		return encuestaRegistrar2(request,m);
	}
	
	@RequestMapping("/pregunta_asignar.html")
	public String preguntaAsignar(HttpServletRequest request,Model m){ 
		List<Encuesta> lEncuesta = null;
		try {
			lEncuesta = encuestaService.listarEncuesta();
		} catch (Exception e) {
			log.error("[preguntaAsignar]",e);
		}
		m.addAttribute("lEncuesta",lEncuesta);
		return "/encuesta/pregunta_asignar";
	}
	@RequestMapping("/pregunta_asignar2.html")
	public String preguntaAsignar2(HttpServletRequest request,Model m){ 
//		List<Encuesta> lEncuesta = null;
//		try {
//			lEncuesta = encuestaService.listarEncuesta();
//		} catch (Exception e) {
//			log.error("[preguntaAsignar]",e);
//		}
//		m.addAttribute("lEncuesta",lEncuesta);
		return "/encuesta/pregunta_asignar2";
	}
//	pregunta_agregar.html
	@SuppressWarnings("unchecked")
	@RequestMapping("/pregunta_agregar.html")
	public String preguntaAgregar(HttpServletRequest request,Model m){ 
		String txtPregunta = request.getParameter("txtPregunta");
		String txtPuntaje = request.getParameter("txtPuntaje");
		log.debug("pregunta:"+txtPregunta);
		log.debug("txtPuntaje:"+txtPuntaje);
		Pregunta pregunta = new Pregunta();
		pregunta.setPregunta(txtPregunta);
		pregunta.setPuntaje(Integer.parseInt(txtPuntaje));
		pregunta.setPreguntaObligatoriaD(0);
		pregunta.setTipoPreguntaD(0); 
		
		HttpSession session = request.getSession(); 
		List<Pregunta> lPreguntaV = (List<Pregunta>) session.getAttribute("session_preguntas");
		if(lPreguntaV==null || lPreguntaV.isEmpty()){ 
			pregunta.setOrden(1);
			lPreguntaV = new ArrayList<Pregunta>();
			lPreguntaV.add(pregunta);
			session.setAttribute("session_preguntas", lPreguntaV);
		}else{
			Pregunta pp =lPreguntaV.get(lPreguntaV.size()-1);
			pregunta.setOrden(pp.getOrden()+1);
			lPreguntaV.add(pregunta);
			session.setAttribute("session_preguntas", lPreguntaV);
		} 
		for (Pregunta p1 : lPreguntaV) {
			log.debug("Pregunta:"+p1.getOrden()+")"+p1.getPregunta()+"|Puntaje="+p1.getPuntaje());
			List<Alternativa> lAlternativa = p1.getAlternativas();
			for (Alternativa a1 : lAlternativa) {
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getPuntaje());
			}
		}
		return "/encuesta/pregunta_listar";
	}
//	pregunta_eliminar
	@RequestMapping("/pregunta_eliminar.html")
	public String preguntaEliminar(HttpServletRequest request,Model m){ 
		Integer idPregunta = Integer.parseInt(request.getParameter("id_p"));
		HttpSession session = request.getSession(); 
		List<Pregunta> lPreguntaV = (List<Pregunta>) session.getAttribute("session_preguntas");
		Pregunta pEliminar = null;
		for (Pregunta p : lPreguntaV) {
			if(p.getOrden()==idPregunta){
				log.debug("Eliminar el idOrden:"+idPregunta+" "+p.getPregunta());
				pEliminar = p;
				break;
			}
		}
		lPreguntaV.remove(pEliminar);
		session.setAttribute("session_preguntas", lPreguntaV);
		for (Pregunta p1 : lPreguntaV) {
			log.debug("Pregunta:"+p1.getOrden()+")"+p1.getPregunta()+"|Puntaje="+p1.getPuntaje());
			List<Alternativa> lAlternativa = p1.getAlternativas();
			for (Alternativa a1 : lAlternativa) {
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getTipoAlternativaD());
			}
		} 
		return preguntaAsignar(request,m);
	}
	
//	pregunta_cbo.html
	@RequestMapping("/pregunta_cbo.html")
	public String preguntaCbo(HttpServletRequest request,Model m){ 
		return "/encuesta/pregunta_cbo_session";
	}
//	pregunta_cargartodo
//	pregunta_cargartodo.html 
	@RequestMapping("/pregunta_cargartodo.html")
	public String preguntaCargarTodo(HttpServletRequest request,Model m){ 
		return "/encuesta/pregunta_listar";
	}
	@RequestMapping("/pregunta_cargarformula.html")
	public String preguntaCargarFormula(HttpServletRequest request,Model m){ 
		return "/encuesta/pregunta_formula";
	}
	
//	"cboPregunta":cboPregunta,
//	"txtAlternativa":txtAlternativa,
//	"cboOpciones":cboOpciones
//	alternativa_agregar
	@RequestMapping("/alternativa_agregar.html")
	public String alternativaAgregar(HttpServletRequest request,Model m){ 
		String cboPregunta = request.getParameter("cboPregunta");
		String txtAlternativa = request.getParameter("txtAlternativa");
		String txtPuntajeAlternativa = request.getParameter("txtPuntajeAlternativa");
		Pregunta p = new Pregunta();
		p.setOrden(Integer.parseInt(cboPregunta));
		Alternativa a = new Alternativa();
		a.setAlternativa(txtAlternativa);
		a.setPregunta(p);
		a.setPuntaje(Integer.parseInt(txtPuntajeAlternativa));
		
		HttpSession session = request.getSession(); 
		List<Pregunta> lPregunta = (List<Pregunta>) session.getAttribute("session_preguntas");
		
		for (Pregunta pregunta : lPregunta) {
			log.debug("p:"+p.getOrden()+"|"+pregunta.getOrden());
			if(p.getOrden()==pregunta.getOrden()){
				List<Alternativa> lAlternativa = pregunta.getAlternativas();
				log.debug("a:"+lAlternativa+"|"+lAlternativa.isEmpty());
				if(lAlternativa==null || lAlternativa.isEmpty()){
					lAlternativa = new ArrayList<Alternativa>();
					a.setOrden(1);
				}else{
					int ultimoId =lAlternativa.get(lAlternativa.size()-1).getOrden() ;
					a.setOrden(ultimoId+1);
				}  
				lAlternativa.add(a);
				log.debug("Agregas:"+a.getAlternativa());
				pregunta.setAlternativas(lAlternativa);
			}
		}
		session.setAttribute("session_preguntas", lPregunta); 
		 
		for (Pregunta p1 : lPregunta) {
			log.debug("Pregunta:"+p1.getOrden()+")"+p1.getPregunta()+"|Puntaje="+p1.getPuntaje());
			List<Alternativa> lAlternativa = p1.getAlternativas();
			for (Alternativa a1 : lAlternativa) {
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getPuntaje());
			}
		} 
		return "/encuesta/pregunta_listar";
	}
//	alternativa_eliminar.html?id_a
	@RequestMapping("/alternativa_eliminar.html")
	public String alternativaEliminar(HttpServletRequest request,Model m){ 
		Integer idAlternativa = Integer.parseInt(request.getParameter("id_a"));
		Integer idPregunta = Integer.parseInt(request.getParameter("id_p"));
		HttpSession session = request.getSession(); 
		List<Pregunta> lPreguntaV = (List<Pregunta>) session.getAttribute("session_preguntas");
//		Pregunta pEliminar = null;
		for (Pregunta p : lPreguntaV) {
			if(p.getOrden()==idPregunta){
				Alternativa aElimnar = null;
				List<Alternativa> lAlternativa = p.getAlternativas();
				for (Alternativa a : lAlternativa) {
					if(a.getOrden()==idAlternativa){
						log.debug("Eliminar el idOrden:"+idAlternativa+" "+a.getAlternativa());
						aElimnar = a;
						break;
					}
				} 
				lAlternativa.remove(aElimnar);
				p.setAlternativas(lAlternativa);
				break;
			}
		} 
		session.setAttribute("session_preguntas", lPreguntaV);
		for (Pregunta p1 : lPreguntaV) {
			log.debug("Pregunta:"+p1.getOrden()+")"+p1.getPregunta()+"|Puntaje="+p1.getPuntaje());
			List<Alternativa> lAlternativa = p1.getAlternativas();
			for (Alternativa a1 : lAlternativa) {
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getTipoAlternativaD());
			}
		} 
		return preguntaAsignar(request,m);
	} 
	@RequestMapping("/asignacionpreguntas_guardar.html")
	public String asignacionPreguntasGuardar(HttpServletRequest request,Model m){ 
		Integer cboEncuesta = Integer.parseInt(request.getParameter("cboEncuesta"));
		HttpSession session = request.getSession(); 
		List<Pregunta> lPreguntaV = (List<Pregunta>) session.getAttribute("session_preguntas");
		if(lPreguntaV==null || lPreguntaV.isEmpty()){
			request.setAttribute("mensaje_alert", "Ingresar como minimo una pregunta");
			return preguntaAsignar(request,m);
		}
		for (Pregunta p : lPreguntaV) {
			if(!(p.getAlternativas().size()>2)){
				request.setAttribute("mensaje_alert", "Ingresar como minimo dos alternativas por pregunta");
				return preguntaAsignar(request,m);
			} 
		}
		if(!log.isDebugEnabled()){
//			encuestaService.registrarPregunta(cboEncuesta, lPreguntaV);
			session.removeAttribute("session_preguntas");
		} 
		return preguntaAsignar(request,m);
	}
	@RequestMapping("/asignacionpreguntas_guardar2.html")
	public String asignacionPreguntasGuardar2(HttpServletRequest request,Model m){ 
		String numeroReferente = request.getParameter("numeroReferente");
		String formula =  request.getParameter("formula");
		log.debug("numeroReferente:"+numeroReferente);
		log.debug("formula:"+formula);
		HttpSession session = request.getSession(); 
		List<Pregunta> lPreguntaV = (List<Pregunta>) session.getAttribute("session_preguntas");
		if(lPreguntaV==null || lPreguntaV.isEmpty()){
			request.setAttribute("mensaje_alert", "Ingresar como minimo una pregunta");
			return preguntaAsignar2(request,m);
		}
		for (Pregunta p : lPreguntaV) {
			if(!(p.getAlternativas().size()>1)){
				request.setAttribute("mensaje_alert", "Ingresar como minimo dos alternativas por pregunta");
				return preguntaAsignar2(request,m);
			} 
		}
		if(numeroReferente==null || numeroReferente.equals("")){
			request.setAttribute("mensaje_alert", "Ingrese numero Referente Valido");
			return preguntaAsignar2(request,m);
		}
		if(formula==null || formula.equals("") ){
			request.setAttribute("mensaje_alert", "Ingrese formula valida");
			return preguntaAsignar2(request,m);
		}
		if(log.isDebugEnabled()){
			log.debug("se borro la session_pregunta");
			NumeroEncuesta ne = new NumeroEncuesta();
			ne.setFecha(new Date());
			ne.setFormula(formula);
			ne.setNumeroReferente(numeroReferente); 
			try {
				encuestaService.registrarNumeroEncuestaAndPreguntasAlternativas(ne, lPreguntaV);
			} catch (Exception e) { 
				log.error("[asignacionPreguntasGuardar2]",e);
			} 
			session.removeAttribute("session_preguntas");
		} 
		return preguntaAsignar2(request,m);
	}
	@RequestMapping("/profesor_asignar.html")
	public String profesorAsignar(HttpServletRequest request,Model m){ 
		List<Encuesta> lEncuesta = null;
		try {
			lEncuesta = encuestaService.listarEncuesta();
		} catch (Exception e) {
			log.error("[profesorAsignar]",e);
		}
		m.addAttribute("lEncuesta",lEncuesta);
		return "/encuesta/profesor_asignar";
	}
//	cboEncuesta 
//	load_profesor
	
//	profesorasiggnar_guardar
	@RequestMapping("/profesorasiggnar_guardar.html")
	public String profesorAsignarGuardar(HttpServletRequest request,Model m){
		Integer idEncuesta = Integer.parseInt(request.getParameter("cboEncuesta"));
		String[] profesores = request.getParameterValues("checkProfesor");
		if(profesores==null || profesores.length==0){
			request.setAttribute("mensaje_error", "Seleccione un Profesor");
			return profesorAsignar(request, m);
		}
		encuestaService.registrarEncuestaProfesor(idEncuesta, profesores);
		return profesorAsignar(request, m);
	}
	
	@RequestMapping("/alumno_asignar.html")
	public String alumnoAsignar(HttpServletRequest request,Model m){ 
		List<Encuesta> lEncuesta = null;
		try {
			lEncuesta = encuestaService.listarEncuesta();
		} catch (Exception e) {
			log.error("[alumnoAsignar]",e);
		}
		m.addAttribute("lEncuesta",lEncuesta);
		return "/encuesta/alumno_asignar";
	}
	
	@RequestMapping("/alumnoasignar_guardar.html")
	public String alumnoAsignarGuardar(HttpServletRequest request,Model m){
		Integer idEncuesta = Integer.parseInt(request.getParameter("cboEncuesta"));
		String txtNumeroAlumnos = request.getParameter("txtNumeroAlumnos");
//		txtNumeroAlumnos
		String[] alumnos = request.getParameterValues("checkAlumno");
		if(alumnos==null || alumnos.length==0){
//			log.debug("=>"+alumnos.length);
			request.setAttribute("mensaje_error", "Seleccione un Alumno");
			return alumnoAsignar(request, m);
		}  
		if(txtNumeroAlumnos.equals("")){
			request.setAttribute("mensaje_error", "Ingrese Numero de Alumnos");
			return alumnoAsignar(request, m);
		}
		Integer idProfesor = Integer.parseInt(request.getParameter("cboProfesor"));
		try {
			encuestaService.registrarEncuestaAlumno(idProfesor,idEncuesta, alumnos,txtNumeroAlumnos);
		} catch (Exception e) { 
			log.error("[alumnoAsignarGuardar]",e);
		}
		return alumnoAsignar(request, m);
	}
	
	@RequestMapping("/encuesta_realizar.html")
	public String encuestaRealizar(HttpServletRequest request,Model m){ 
		HttpSession session = request.getSession();
		Usuario usuSession = (Usuario)session.getAttribute("session_usuario");
		List<EncuestaAlumno> lEncuestaAlumno = null;
		try {
			log.debug("Usuario:"+usuSession.getIdUsuario());
			lEncuestaAlumno = usuarioService.listarEncuestaAlumnoXUsuario(usuSession);
			log.debug("Usuario:"+usuSession.getIdUsuario()+"|"+lEncuestaAlumno);
			if(lEncuestaAlumno==null || lEncuestaAlumno.isEmpty()){
				request.setAttribute("totalEncuestaAlumno", 0);
				return "/encuesta/encuesta_realizar";
			}
			log.debug("tam:"+lEncuestaAlumno.size());
			request.setAttribute("totalEncuestaAlumno", lEncuestaAlumno.size());
			request.setAttribute("lEncuestaAlumno", lEncuestaAlumno);
		} catch (Exception e) { 
			log.error("[encuestaRealizar]",e);
		} 
		return "/encuesta/encuesta_realizar";
	}
	
//	encuesta_inicio.html
	@RequestMapping("/encuesta_inicio.html")
	public String encuestaInicio(HttpServletRequest request,Model m){ 
		String idEncuestaAlumno = request.getParameter("cboEncuestaAlumno");
		String idEncuestaAlumno2 = (String) request.getAttribute("cboEncuestaAlumno");
		log.debug("id1:"+idEncuestaAlumno);
		log.debug("id2:"+idEncuestaAlumno2);
		if(idEncuestaAlumno==null){
			idEncuestaAlumno = idEncuestaAlumno2;
		}
		
		try { 
			
			
			EncuestaAlumno ea = encuestaService.getEncuestaAlumno(idEncuestaAlumno);
			ea.setInicioEncuesta(new Date());
			ea.setResolvioD(1); 
			ea = encuestaService.actualizarEncuestaAlumno(ea); 
			
			NumeroEncuesta ne = ea.getEncuestaprofesor().getEncuesta().getNumeroencuesta();
			List<Pregunta> lPregunta = encuestaService.listarPreguntaXNumeroEncuesta(ne);
			for (Pregunta p : lPregunta) {
				List<Alternativa> lAlternativa = encuestaService.listarAlternativaXPregunta(p);
				p.setAlternativas(lAlternativa);
			}
			
			for (Pregunta pregunta : lPregunta) {
				List<Alternativa> alternativa1 = pregunta.getAlternativas();//pregunta.getIdPregunta()
				log.debug("Pregunta=>"+pregunta.getOrden()+""+pregunta.getPregunta()+" (Puntaje:"+pregunta.getPuntaje()+")");
				for (Alternativa alternativa : alternativa1) {
//					alternativa.getIdAlternativas()
					log.debug("Alternativa=>"+alternativa.getOrden()+")"+alternativa.getAlternativa()+"("+(alternativa.getTipoAlternativaD()==1?"F":"V")+")");
				}
			}
			request.setAttribute("lPregunta", lPregunta);
			request.setAttribute("idEncuestaAlumno", ea.getIdEncuestaAlumno());
			 
		} catch (Exception e) { 
			log.error("[encuestaInicio]",e);
		} 
		return "/encuesta/encuesta_inicio";
	}
	@RequestMapping("/encuesta_fin.html")
	public String encuestaFin(HttpServletRequest request,Model m){ 
		String idEncuestaAlumno = request.getParameter("idEncuestaAlumno");
		try {  
			
			EncuestaAlumno ea = encuestaService.getEncuestaAlumno(idEncuestaAlumno);
			
			NumeroEncuesta ne = ea.getEncuestaprofesor().getEncuesta().getNumeroencuesta();
			List<Pregunta> lPregunta = encuestaService.listarPreguntaXNumeroEncuesta(ne);
			List<String> respuestas = new ArrayList<String>();
			for (Pregunta p : lPregunta) {
				String respuesta = request.getParameter(p.getIdPregunta()+"");
				if(respuesta==null){
					request.setAttribute("cboEncuestaAlumno", idEncuestaAlumno);
					request.setAttribute("mensaje_error", "Es obligatorio responder todo las preguntas");
					return encuestaInicio(request, m);
				} 
				respuestas.add(respuesta);
			}  
			
			ea.setFinEncuesta(new Date());
			ea.setResolvioD(2); 
			ea = encuestaService.actualizarEncuestaAlumno(ea);
			
			String[] respuestasArreglo = (String[]) respuestas.toArray(new String[]{""});
			encuestaService.registrarMarcadas(ea, respuestasArreglo);
			request.setAttribute("ea", ea);
			 
		} catch (Exception e) { 
			log.error("[encuestaFin]",e);
		} 
		return "/encuesta/encuesta_fin";
	}
//	encuesta/encuesta_nota.html
	@RequestMapping("/encuesta_nota.html")
	public String encuestaNota(HttpServletRequest request,Model m){ 
		HttpSession session = request.getSession();
		Usuario profesor = (Usuario) session.getAttribute("session_usuario");
		List<EncuestaProfesor> lEncuestaProfesor = encuestaService.listarEncuestasXprofesor(profesor);
		if(lEncuestaProfesor==null || lEncuestaProfesor.isEmpty()){
			request.setAttribute("totalEncuestaProfesor", "0");
			return "/encuesta/encuesta_nota";
		}
		request.setAttribute("totalEncuestaProfesor", lEncuestaProfesor.size());
		request.setAttribute("lEncuestaProfesor", lEncuestaProfesor);
		for (EncuestaProfesor ep : lEncuestaProfesor) {
//			ep.getEncuesta().getAnio().getCurso().getCurso()
			log.debug("ep:"+ep.getIdEncuestaProfesor()+"|"+ep.getEncuesta().getNombreReferente());
		}
		return "/encuesta/encuesta_nota";
	}
//	encuesta_nota_mostrar
	@RequestMapping("/encuesta_nota_mostrar.html")
	public String encuestaNotaMostrar(HttpServletRequest request,Model m){ 
		String idEncuestaProfesor = request.getParameter("cboEncuestaProfesor");
		
		
		EncuestaProfesor ep =  encuestaService.getEncuestaProfesor(Integer.parseInt(idEncuestaProfesor));
		
		String formula = ep.getEncuesta().getNumeroencuesta().getFormula();
		NumeroEncuesta ne = ep.getEncuesta().getNumeroencuesta();
		List<Pregunta> lPreguntas = encuestaService.listarPreguntaXNumeroEncuesta(ne);
		Map<String, Integer> mapVariablePuntaje = new HashMap<String, Integer>();
		int nPregunta=1; 
		for (Pregunta p : lPreguntas) { 
			Integer puntajePregunta = p.getPuntaje();
			mapVariablePuntaje.put("P"+nPregunta, puntajePregunta);
			List<Alternativa> lAlternativas = encuestaService.listarAlternativaXPregunta(p);
			int nAlternativa=1;
			for (Alternativa a : lAlternativas) {
				Integer puntajeAlternativa = a.getPuntaje(); 
				mapVariablePuntaje.put("P"+nPregunta+"_"+nAlternativa, puntajeAlternativa);
				nAlternativa++;
			}
			nPregunta++;
		}
		Set<Entry<String, Integer>> s = mapVariablePuntaje.entrySet();
		Iterator<Entry<String, Integer>> i = s.iterator();
		String formulaReemplazada = formula;
		while (i.hasNext()) {
			Entry<String, Integer> e = i.next();
			String k = e.getKey();
			Integer v = e.getValue();
			log.debug("Entry:"+k+"="+v);
			formulaReemplazada = formulaReemplazada.replaceAll(k, v+"");
		}
		log.debug("Formula de la Nota perfecta:"+formulaReemplazada);
		
		double puntajeMaximo = Double.parseDouble(Utiles.getResultado(formulaReemplazada));
		log.debug("Nota perfecta:"+puntajeMaximo);
		
//		Map<String, Integer> mapVariablePuntaje2 = new HashMap<String, Integer>();
		
		List<EncuestaAlumno> lEncuestaAlumno = encuestaService.listarEncuestasAlumnoXIdEncuestaProfesor(ep);
		Integer tamMinimoDeAlumno = Integer.parseInt(lEncuestaAlumno.get(0).getNumeroAlumnosValidos());
		
		double sumaNotas = 0;
		double totalAlumnos = lEncuestaAlumno.size();
		for (EncuestaAlumno ea : lEncuestaAlumno) {
			Usuario alumno = ea.getUsuario();
			
			List<Marcada> lMarcadas = encuestaService.listarMarcadasXEncuestaAlumno(ea);
			int nPregunta2=1; 
			Boolean val  = true;
			for (Pregunta p2 : lPreguntas) { 
				Integer puntajePregunta2 = p2.getPuntaje();
				mapVariablePuntaje.put("P"+nPregunta2, puntajePregunta2);
				List<Alternativa> lAlternativas2 = encuestaService.listarAlternativaXPregunta(p2);
				int nAlternativa2=1;
				for (Alternativa a2 : lAlternativas2) {
					val=true;
					for (Marcada marcada : lMarcadas) {
						if(marcada.getAlternativa().getIdAlternativas()==a2.getIdAlternativas()){
							Integer puntajeAlternativa2 = a2.getPuntaje(); 
							mapVariablePuntaje.put("P"+nPregunta2+"_"+nAlternativa2, puntajeAlternativa2);
							nAlternativa2++;
							val= false;
							break;
						} 
					}
					if(val){
						Integer puntajeAlternativa2 = 0; 
						mapVariablePuntaje.put("P"+nPregunta2+"_"+nAlternativa2, puntajeAlternativa2);
						nAlternativa2++;	
					} 
				}
				nPregunta2++;
			} 
			
			log.debug("Usuario:"+alumno.getIdUsuario());
			log.debug("Variables Reemplazadas:"+mapVariablePuntaje.toString()); 
			Iterator<Entry<String, Integer>> i2 = mapVariablePuntaje.entrySet().iterator();
			String formulaReemplazada2 = formula;
			while (i2.hasNext()) {
				Entry<String, Integer> e = i2.next();
				String k = e.getKey();
				Integer v = e.getValue();
				log.debug("Entry:"+k+"="+v);
				formulaReemplazada2 = formulaReemplazada2.replaceAll(k, v+"");
			}
			log.debug("Formula de la Nota perfecta:"+formulaReemplazada2);
			
			double puntajeAlumno = Double.parseDouble(Utiles.getResultado(formulaReemplazada2));
			log.debug("Nota alumno:"+puntajeAlumno);
			double notaAlumno = (puntajeAlumno*20)/puntajeMaximo;
			sumaNotas = sumaNotas+notaAlumno;
			
		}  
		BigDecimal d1 = new BigDecimal(sumaNotas); 
		BigDecimal d2 = new BigDecimal(totalAlumnos);
		BigDecimal dR = d1.divide(d2);
		log.debug("Es valido:"+dR);
		Double notaPromedioXcurso = sumaNotas/totalAlumnos;
		boolean promedioValido = tamMinimoDeAlumno<=totalAlumnos;
//		log.debug("Es valido:"+notaPromedioXcurso+", es:"+notaPromedioXcurso);
//		BigDecimal notaPromedioXcursoBD = new BigDecimal(notaPromedioXcurso);
		
		request.setAttribute("promedioValido", promedioValido);
		request.setAttribute("notaPromedioXcurso", dR);
		
		
		return "/encuesta/encuesta_notaresultado";
	}
	
	public static void main(String[] args) {
 
		ELProcessor elp = new ELProcessor();
		String campoDB="((((((98*63*22)/1000)+((98*63*14)/1000)-((35*15*16)/1000))))*2)";
		Object ret = elp.eval(campoDB);
		System.out.println(ret+"");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("P1", 10);
		map.put("P1_1", 100);
		map.put("P1_2", 0);
		
		map.put("P2", 20);
		map.put("P2_1", 50);
		map.put("P2_2", 10); 
 
		System.out.println(map.toString());
//		map2 = map;
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		
		map2.put("P1", 8880);
		System.out.println(map2.toString());
		 
		System.out.println(map.toString()); 
		
		
		
		
//		for (int j = 1; j < 5; j++) {//pregunta
//			map2.put("P"+j, 1);
			for (int j2 = 1; j2 < 3; j2++) {//alternativa
				
				for (int k = 1; k < 5 ; k++) {//marcada
					
				}
			}
//		}
	}
	
}
