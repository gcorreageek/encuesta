package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the carrera database table.
 * 
 */
@Entity
@NamedQuery(name="Carrera.findAll", query="SELECT c FROM Carrera c")
@NamedQueries({  
	@NamedQuery(name="Carrera.findId", query="SELECT u FROM Carrera u WHERE u.idCarrera = ?1"),
	@NamedQuery(name="Carrera.findDescripcion", query="SELECT u FROM Carrera u WHERE u.descripcion like ?1")
})
public class Carrera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCarrera;

	private String descripcion;

	private String nombre;

	private String obervacion;

	@Column(name="tipo_carrera_d")
	private int tipoCarreraD;

	//bi-directional many-to-one association to Anio
	@OneToMany(mappedBy="carrera")
	private List<Anio> anios;

	public Carrera() {
	}

	public int getIdCarrera() {
		return this.idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
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

	public String getObervacion() {
		return this.obervacion;
	}

	public void setObervacion(String obervacion) {
		this.obervacion = obervacion;
	}

	public int getTipoCarreraD() {
		return this.tipoCarreraD;
	}

	public void setTipoCarreraD(int tipoCarreraD) {
		this.tipoCarreraD = tipoCarreraD;
	}

	public List<Anio> getAnios() {
		return this.anios;
	}

	public void setAnios(List<Anio> anios) {
		this.anios = anios;
	}

	public Anio addAnio(Anio anio) {
		getAnios().add(anio);
		anio.setCarrera(this);

		return anio;
	}

	public Anio removeAnio(Anio anio) {
		getAnios().remove(anio);
		anio.setCarrera(null);

		return anio;
	}

}