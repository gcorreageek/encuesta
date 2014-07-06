package com.encuesta.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encuesta.dao.DominioDAO;
import com.encuesta.model.Dominio;
@Service
public class DominioServiceImpl implements DominioService {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
    DominioDAO dao; 
	
	@Override
	@Transactional
	public Dominio insertar(Dominio a) throws Exception {
		return dao.insertarOActualizar(a);
	}

	@Override
	@Transactional
	public Dominio actualiza(Dominio a) throws Exception {
		return dao.insertarOActualizar(a);
	}
	
	@Override
	@Transactional
	public Dominio desabilitarOHabilitar(Dominio a) throws Exception { 
		List<Dominio> l=buscarXIdDominio(a);
		a = l.get(0); 
		if("HABILITADO".equals(a.getHabilitado())){
			a.setHabilitado("DESABILITADO");
		}else{
			a.setHabilitado("HABILITADO");
		} 
		return dao.insertarOActualizar(a);
	}

	@Override
	public List<Dominio> listar() throws Exception {
		Dominio a = new Dominio();
		a.setCampo("%");
		return dao.buscarXCampo(a);
	}

	@Override
	public List<Dominio> buscarXIdDominio(Dominio a) throws Exception {
		return dao.buscarXId(a);
	}

	@Override
	public List<Dominio> buscarXCampo(Dominio a) throws Exception {
		log.debug(""+a.getCampo());
		if(a.getCampo()==null){
			log.debug("campo es = null");
		}
		a.setCampo("%"+(a.getCampo()==null?"":a.getCampo())+"%");
		log.debug("=>"+a.getCampo());
		return dao.buscarXCampo(a);
	}

	

}
