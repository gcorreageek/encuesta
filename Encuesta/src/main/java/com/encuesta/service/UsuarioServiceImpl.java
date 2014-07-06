package com.encuesta.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encuesta.dao.UsuarioDAO;
import com.encuesta.model.Usuario;
@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
    UsuarioDAO dao; 
	
	@Override
	@Transactional
	public Usuario insertar(Usuario a) throws Exception {
		return dao.insertarOActualizar(a);
	}

	@Override
	@Transactional
	public Usuario actualiza(Usuario a) throws Exception {
		return dao.insertarOActualizar(a);
	}

	@Override
	public List<Usuario> listar() throws Exception {
		Usuario a = new Usuario();
		a.setUserName("");
		return dao.buscarXUserName(a);
	}

	@Override
	public List<Usuario> buscarXIdUsario(Usuario a) throws Exception {
		return dao.buscarXId(a);
	}

	@Override
	public List<Usuario> buscarXUserName(Usuario a) throws Exception {
		return dao.buscarXUserName(a);
	}

	@Transactional
	@Override
	public Object insertarMuchos(List<Usuario> lstUsuario) {
		try {
			for (Usuario usuario : lstUsuario) {
				log.debug("usuario1:"+usuario.getIdUsuario());
				log.debug("usuario1:"+usuario.getNombre());
				usuario.setIdUsuario(0);
				insertar(usuario);
			}
			return true;
		} catch (Exception e) {
			log.error("[insertarMuchos]",e);
			return false;
		}  
	}

}
