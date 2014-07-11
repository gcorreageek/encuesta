package com.encuesta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encuesta.model.Anio;
import com.encuesta.model.AsignacionProfesor;
import com.encuesta.model.Carrera;
import com.encuesta.model.Ciclo;
import com.encuesta.model.Curso;
import com.encuesta.model.Matricula;
import com.encuesta.service.MatriculaService;
@RequestMapping("/matricula")
@Controller
public class MatriculaController {

	private final Log log = LogFactory.getLog(getClass());
	
//	@Autowired
//	UsuarioService usuarioService;
	@Autowired
	MatriculaService matriculaService;
//	@Autowired
//	EncuestaService encuestaService;
//	@Autowired
//	DominioService dominioService;
 
	
	@RequestMapping("/ir.html")
	public String ir(HttpServletRequest request,Model m){ 
		return "/matricula/matricula_registrar";
	}
  
	@RequestMapping("/load_carrera.html")
	public String cargarCarrera(HttpServletRequest request,Model m){ 
		List<Carrera> lCarrera = null;
		try {
			lCarrera = matriculaService.listarCarrera();
			for (Carrera x : lCarrera) {
				log.debug("Carrera:"+x.getIdCarrera()+"|"+x.getDescripcion());
			}
		} catch (Exception e) {
			log.error("[cargarCarrera]",e);
		}
		m.addAttribute("listarCarrera", lCarrera);
		return "/matricula/cbo_carrera";
	}
	@RequestMapping("/load_curso.html")
	public String cargarCurso(HttpServletRequest request,Model m){ 
		List<Curso> lCurso = null;
		try {
			lCurso = matriculaService.listarCurso();
			for (Curso x : lCurso) {
				log.debug("Carrera:"+x.getIdCurso()+"|"+x.getCurso());
			}
		} catch (Exception e) {
			log.error("[cargarCurso]",e);
		}
		m.addAttribute("listarCurso", lCurso);
		return "/matricula/cbo_curso";
	}
	
	@RequestMapping("/anio_buscar.html")
	public String anioBuscar(HttpServletRequest request,Model m){ 
		Integer idModalidadD = Integer.parseInt(request.getParameter("idModalidadD")) ;
		Integer idAnioD = Integer.parseInt(request.getParameter("idAnioD")) ;
		Integer idNumeroD = Integer.parseInt(request.getParameter("idNumeroD")) ;
		
		Integer idCarrera = Integer.parseInt(request.getParameter("idCarrera")) ;
		Integer idCicloD = Integer.parseInt(request.getParameter("idCicloD")) ;
		Integer idCurso = Integer.parseInt(request.getParameter("idCurso")) ;
		Integer idSeccionD = Integer.parseInt(request.getParameter("idSeccionD")) ;
		
		List<Anio> lAnio = null;
		try {
			Ciclo ciclo = new Ciclo();
			ciclo.setModalidadD(idModalidadD);
			ciclo.setAnnioD(idAnioD);
			ciclo.setNumeroD(idNumeroD); 
			
			Carrera carrera = new Carrera();
			carrera.setIdCarrera(idCarrera);
			
			Curso curso = new Curso();
			curso.setIdCurso(idCurso);
			
			
			Anio a = new Anio();
			a.setCiclo(ciclo);
			a.setCarrera(carrera);
			a.setCurso(curso);
			a.setSessionD(idSeccionD);
			a.setCicloAcademicoD(idCicloD);
			
			lAnio = matriculaService.buscarAnio(a);
			for (Anio x : lAnio) {
				log.debug("Curso:"+x.getCiclo().getNombre()+"|"+x.getCarrera().getDescripcion()
						+"|"+x.getCicloAcademidoDominio().getValor()+"|"+x.getCurso().getCurso()
						+"|"+x.getSessionDominio().getValor());
			}
		} catch (Exception e) {
			log.error("[anioBuscar]",e);
		}
		m.addAttribute("buscarAnio", lAnio);
		return "/matricula/anio_buscar";
	}
	
	@RequestMapping("/profesor_listar.html")
	public String profesorListar(HttpServletRequest request,Model m){
		String cboEncuesta = request.getParameter("cboEncuesta");
		
		List<AsignacionProfesor> lProfesorCurso = null;
		try {
			lProfesorCurso = matriculaService.listarProfesoresAsignados(cboEncuesta);
		} catch (Exception e) {
			log.error("[profesorListar]",e);
		} 
		m.addAttribute("lProfesorCurso",lProfesorCurso);
		return "/matricula/profesor_listar";
	}
//	profesor_cbo
	@RequestMapping("/profesor_cbo.html")
	public String profesorCbo(HttpServletRequest request,Model m){
		String cboEncuesta = request.getParameter("cboEncuesta");
		
		List<AsignacionProfesor> lProfesor = null;
		try {
			lProfesor = matriculaService.listarProfesoresAsignados(cboEncuesta);
//			for (AsignacionProfesor x : lProfesor) {
//				log.debug("=>"+x.getUsuario().getIdUsuario()
//						+x.getUsuario().getApellidoPaterno()
//						+x.getUsuario().getApellidoMaterno()
//						+x.getUsuario().getNombre()
//						+x.getUsuario().getCodigo());
//			}
		} catch (Exception e) {
			log.error("[profesorCbo]",e);
		} 
		m.addAttribute("lProfesor",lProfesor);
		return "/matricula/cbo_profesor";
	}
//	alumno_listar
	@RequestMapping("/alumno_listar.html")
	public String alumnoListar(HttpServletRequest request,Model m){
		String cboEncuesta = request.getParameter("cboEncuesta");
		String cboProfesor = request.getParameter("cboProfesor");
		List<Matricula> lMatricula = null;
		try {
			lMatricula = matriculaService.listarAlumnosMatriculados(cboEncuesta,cboProfesor);
		} catch (Exception e) {
			log.error("[alumnoListar]",e);
		} 
		m.addAttribute("lMatricula",lMatricula);
		return "/matricula/alumno_listar";
	}
}
