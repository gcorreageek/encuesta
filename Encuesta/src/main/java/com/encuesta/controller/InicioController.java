package com.encuesta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encuesta.model.Acceso;
import com.encuesta.model.Cargo;
import com.encuesta.model.Usuario;
import com.encuesta.service.UsuarioService;
import com.encuesta.util.Constantes;

@Controller 
public class InicioController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UsuarioService usuarioService;
	
	
	
	@RequestMapping("/inicio.html"  ) 
	public String inicio(HttpServletRequest request,Model m) {   
		log.info("El inicio!"); 
		HttpSession session = request.getSession();
		String ruta = (String) session.getAttribute("session_ruta");
		Usuario usuSession = (Usuario) session.getAttribute("session_usuario");
		if(ruta==null || "".equals(ruta)){
			session.setAttribute("session_ruta", Constantes.RUTA_WEB_LIB);
		} 
		log.debug("usuarios:"+usuSession);
		if(usuSession!=null){
			log.debug("usuarios:"+usuSession.getUserName());
			return "/seguridad/index";
		}   
		m.addAttribute("usuario", new Usuario());
		return "seguridad/login"; 
	}
	
	@RequestMapping("/cambiar.html"  )
	public String cambiar(HttpServletRequest request) {  
		log.info("cambiar");  
		return "cambiar"; 
	}
	@RequestMapping("/cambiar_ruta.html"  )
	public String cambiarRuta(HttpServletRequest request) {  
		log.info("cambiar ruta lib");
		String ruta = request.getParameter("ruta");
		log.info("ruta"+ruta);
		HttpSession session = request.getSession();
		String rutaSession = (String) session.getAttribute("session_ruta");
		
		if(rutaSession==null){
			session.setAttribute("session_ruta", ruta);
		}else{
			session.removeAttribute("session_ruta");
			session.setAttribute("session_ruta", ruta);
		}  
		return "cambiar"; 
	}
	
	@RequestMapping("/logueo.html"  ) 
	public String logueo(Model m,HttpServletRequest request,Usuario usuarioViene) {   
		log.info("El logueo!");
		Usuario u = (Usuario) usuarioViene;
		log.info("Usuario:"+u.getUserName());
		log.info("Pass:"+u.getPassName());
		HttpSession session = request.getSession();
		
		try {
			Usuario usuarioV = (Usuario) session.getAttribute("session_usuario");
			if(usuarioV!=null){
				return "seguridad/index"; 
			}
			List<Usuario> lstUsuario= usuarioService.buscarXUserName(u);
			if(!lstUsuario.isEmpty()){
				Usuario uHere = lstUsuario.get(0); 
				if(uHere.getPassName().equals(u.getPassName())){
					log.info("El Usuario y la Contrasena OK!");
					Cargo cargo =uHere.getCargoDelUsuario(); 
					log.debug("cargo:"+cargo.getCargo());
					List<Acceso> accesos =usuarioService.listarAccesosXIdCargo(cargo);
					log.debug("cargo:"+accesos.isEmpty());
					for (Acceso acc : accesos) {
						log.debug("menus:"+acc.getMenu().getNomMenu());
						log.debug("menus:"+acc.getMenu().getUrlMenu()); 
					}  
					session.setAttribute("session_usuario", uHere);
					session.setAttribute("usuario_accesos", accesos);
					return "seguridad/index"; 
				}else{
					log.info("El Contrasena esta mal!");
					m.addAttribute("mensajeErrorUsuario", "El Contrasena esta mal!");
				}
			}else{
				log.info("El Usuario no existe");
				m.addAttribute("mensajeErrorUsuario", "El Usuario no existe");
			}
			
		} catch (Exception e) { 
			log.error("[logueo]",e);
			m.addAttribute("mensajeErrorUsuario", "Ocurrio un error inexperado, porfavor reporte el error!");
		}
		return "seguridad/login"; 
	}
	
	
	@RequestMapping("/des_logueo.html"  ) 
	public String desLogueo(HttpServletRequest request,Model m) {
		log.info("==>desLogueo");
		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("session_usuario");
		log.debug("Usuario:"+u.getPassName());
		if(u!=null){  
			log.debug("session1");
			session.removeAttribute("session_usuario");
			session.removeAttribute("usuario_accesos"); 
			log.debug("session2");
			return inicio(request,m);	
		}
		return "seguridad/bienvenidos"; 

	}
}
