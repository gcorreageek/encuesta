package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the encuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Encuesta.findAll", query="SELECT e FROM Encuesta e")
@NamedQueries({ // findCicloXIdAnio
	@NamedQuery(name="Encuesta.findId", query="SELECT u FROM Encuesta u WHERE u.idEncuesta = ?1 "), 
	@NamedQuery(name="Encuesta.findNombreReferente", query="SELECT u FROM Encuesta u WHERE u.nombreReferente like ?1 ")
})
public class Encuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEncuesta;
	
	private String nombreReferente;

	//bi-directional many-to-one association to Anio
	@ManyToOne
	@JoinColumn(name="idAnio")
	private Anio anio;

	//bi-directional many-to-one association to Tipoencuesta
	@ManyToOne
	@JoinColumn(name="idTipoEncuesta")
	private TipoEncuesta tipoencuesta;

	//bi-directional many-to-one association to Numeroencuesta
	@ManyToOne
	@JoinColumn(name="idNumeroEncuesta")
	private NumeroEncuesta numeroencuesta;

	//bi-directional many-to-one association to Encuestaprofesor
	@OneToMany(mappedBy="encuesta")
	private List<EncuestaProfesor> encuestaprofesors;

	public Encuesta() {
	}

	public int getIdEncuesta() {
		return this.idEncuesta;
	}

	public void setIdEncuesta(int idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public Anio getAnio() {
		return this.anio;
	}

	public void setAnio(Anio anio) {
		this.anio = anio;
	}

	public TipoEncuesta getTipoencuesta() {
		return this.tipoencuesta;
	}

	public void setTipoencuesta(TipoEncuesta tipoencuesta) {
		this.tipoencuesta = tipoencuesta;
	}

	public NumeroEncuesta getNumeroencuesta() {
		return this.numeroencuesta;
	}

	public void setNumeroencuesta(NumeroEncuesta numeroencuesta) {
		this.numeroencuesta = numeroencuesta;
	}

	public List<EncuestaProfesor> getEncuestaprofesors() {
		return this.encuestaprofesors;
	}

	public void setEncuestaprofesors(List<EncuestaProfesor> encuestaprofesors) {
		this.encuestaprofesors = encuestaprofesors;
	}

	public EncuestaProfesor addEncuestaprofesor(EncuestaProfesor encuestaprofesor) {
		getEncuestaprofesors().add(encuestaprofesor);
		encuestaprofesor.setEncuesta(this);

		return encuestaprofesor;
	}

	public EncuestaProfesor removeEncuestaprofesor(EncuestaProfesor encuestaprofesor) {
		getEncuestaprofesors().remove(encuestaprofesor);
		encuestaprofesor.setEncuesta(null);

		return encuestaprofesor;
	}

	public String getNombreReferente() {
		return nombreReferente;
	}

	public void setNombreReferente(String nombreReferente) {
		this.nombreReferente = nombreReferente;
	}

}