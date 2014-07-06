package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aula database table.
 * 
 */
@Entity
@NamedQuery(name="Aula.findAll", query="SELECT a FROM Aula a")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAula;

	private String capacidad;

	private String descripcion;

	private String nombre;

	private String observacion;

	@Column(name="tipo_aula_d")
	private int tipoAulaD;

	public Aula() {
	}

	public int getIdAula() {
		return this.idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public String getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getTipoAulaD() {
		return this.tipoAulaD;
	}

	public void setTipoAulaD(int tipoAulaD) {
		this.tipoAulaD = tipoAulaD;
	}

}