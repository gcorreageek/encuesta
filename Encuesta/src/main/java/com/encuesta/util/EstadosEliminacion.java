package com.encuesta.util;

public enum EstadosEliminacion {
	SIN_ESTADO(0), ELIMINADO(1), REGISTRADO(2) ;

	private Integer estadosEliminacion;
	private EstadosEliminacion(Integer s) {
		estadosEliminacion = s;
	}
	public Integer getEstadosEliminacion() {
		return estadosEliminacion;
	} 
}
 