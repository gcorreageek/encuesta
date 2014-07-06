package com.encuesta.service;

import java.util.List;

import com.encuesta.model.Dominio;

public interface DominioService {

	Dominio insertar(Dominio a)throws Exception;
	Dominio actualiza(Dominio a)throws Exception; 
	Dominio desabilitarOHabilitar(Dominio a)throws Exception; 
	List<Dominio> listar()throws Exception;
	List<Dominio> buscarXIdDominio(Dominio a)throws Exception; 
	List<Dominio> buscarXCampo(Dominio a)throws Exception;
}
