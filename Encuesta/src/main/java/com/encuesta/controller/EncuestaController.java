package com.encuesta.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.encuesta.model.NumeroEncuesta;
import com.encuesta.model.Pregunta;
import com.encuesta.model.Usuario;
import com.encuesta.service.DominioService;
import com.encuesta.service.EncuestaService;
import com.encuesta.service.MatriculaService;
import com.encuesta.service.UsuarioService;
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
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getTipoAlternativaD());
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
	
//	"cboPregunta":cboPregunta,
//	"txtAlternativa":txtAlternativa,
//	"cboOpciones":cboOpciones
//	alternativa_agregar
	@RequestMapping("/alternativa_agregar.html")
	public String alternativaAgregar(HttpServletRequest request,Model m){ 
		String cboPregunta = request.getParameter("cboPregunta");
		String txtAlternativa = request.getParameter("txtAlternativa");
		String cboOpciones = request.getParameter("cboOpciones");
		Pregunta p = new Pregunta();
		p.setOrden(Integer.parseInt(cboPregunta));
		Alternativa a = new Alternativa();
		a.setAlternativa(txtAlternativa);
		a.setPregunta(p);
		a.setTipoAlternativaD(Integer.parseInt(cboOpciones));
		
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
				log.debug("Alternativa:"+a1.getAlternativa()+"|"+a1.getTipoAlternativaD());
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
			return preguntaAsignar(request,m);
		}
		encuestaService.registrarPregunta(cboEncuesta, lPreguntaV);
		session.removeAttribute("session_preguntas");
		return preguntaAsignar(request,m);
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
		
		String[] alumnos = request.getParameterValues("checkAlumno");
		log.debug("=>"+alumnos.length);
		Integer idProfesor = Integer.parseInt(request.getParameter("cboProfesor"));
		try {
			encuestaService.registrarEncuestaAlumno(idProfesor,idEncuesta, alumnos);
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
			request.setAttribute("lEncuestaAlumno", lEncuestaAlumno);
			
			for (EncuestaAlumno encuestaAlumno : lEncuestaAlumno) {
				log.debug("Encuesta:"+
			encuestaAlumno.getEncuestaprofesor().getUsuario().getApellidoPaterno()+" "+
			encuestaAlumno.getEncuestaprofesor().getUsuario().getApellidoMaterno()+" "+
			encuestaAlumno.getEncuestaprofesor().getUsuario().getNombre()+"-"+
			encuestaAlumno.getEncuestaprofesor().getEncuesta().getAnio().getCurso().getCurso());
//				NumeroEncuesta ne = encuestaAlumno.getEncuestaprofesor().getEncuesta().getNumeroencuesta();
//				log.debug("Ne:"+ne.getIdNumeroEncuesta());
//				List<Pregunta> lPregunta = encuestaService.listarPreguntaXNumeroEncuesta(ne);
//				for (Pregunta p : lPregunta) {
//					log.debug("P:"+p.getIdPregunta()+"|"+p.getPregunta());
//					List<Alternativa> lAlternativa = encuestaService.listarAlternativaXPregunta(p);
//					for (Alternativa a : lAlternativa) {
//						log.debug("Alternativas:"+a.getIdAlternativas()+"|"+a.getAlternativa());
//					}
//				}
			}
		} catch (Exception e) { 
			log.error("[encuestaRealizar]",e);
		} 
		return "/encuesta/encuesta_realizar";
	}
	
//	encuesta_inicio.html
	@RequestMapping("/encuesta_inicio.html")
	public String encuestaInicio(HttpServletRequest request,Model m){ 
		String idEncuestaAlumno = request.getParameter("cboEncuestaAlumno");
		 
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
			ea.setFinEncuesta(new Date());
			ea.setResolvioD(2); 
			ea = encuestaService.actualizarEncuestaAlumno(ea);
			 
			NumeroEncuesta ne = ea.getEncuestaprofesor().getEncuesta().getNumeroencuesta();
			List<Pregunta> lPregunta = encuestaService.listarPreguntaXNumeroEncuesta(ne);
			List<String> respuestas = new ArrayList<String>();
			for (Pregunta p : lPregunta) {
				String respuesta = request.getParameter(p.getIdPregunta()+"");
				if(respuesta!=null){
					respuestas.add(respuesta);
				} 
			}  
			String[] respuestasArreglo = (String[]) respuestas.toArray(new String[]{""});
			encuestaService.registrarMarcadas(ea, respuestasArreglo);
			request.setAttribute("ea", ea);
			 
		} catch (Exception e) { 
			log.error("[encuestaFin]",e);
		} 
		return "/encuesta/encuesta_fin";
	}
	
	
	
	
	
	
}
