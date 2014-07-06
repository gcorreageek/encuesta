package com.encuesta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encuesta.model.Dominio;
import com.encuesta.service.DominioService;

@Controller 
public class DominioController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	DominioService dominioService;
	
	
	
	@RequestMapping("/inicio_dominio_buscar.html"  ) 
	public String inicio(Model m,Dominio d,HttpServletRequest request) {   
		log.info("El inicio!");
		String campo = d.getCampo();
		log.debug("campo1:"+d.getCampo());
		log.debug("campo2:"+request.getParameter("campo")); 
		List<Dominio> lstDominio = null;
		try { 
			lstDominio= dominioService.buscarXCampo(d);
		} catch (Exception e) {
			log.error("[inicio]",e);
		} 
		m.addAttribute("lstDominio", lstDominio);
		d.setCampo(campo);
		m.addAttribute("dominio", d);
		return "mantenimiento/dominio_buscar"; 
	}
	
//	dominio_eliminar.html
	@RequestMapping(value="/dominio_eliminar.html")
	public String dominioEliminar(Model m,Dominio d,HttpServletRequest request) { 
		String campo = d.getCampo();
		log.debug("campo:"+campo);
		try {
			dominioService.desabilitarOHabilitar(d);
			d.setCampo(campo);
		} catch (Exception e) {
			log.error("[dominioEliminar]",e);
		}
		return inicio(m,d,request);
	}
	
 
}
