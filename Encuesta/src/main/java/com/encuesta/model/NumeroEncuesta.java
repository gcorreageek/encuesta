package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the numeroencuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Numeroencuesta.findAll", query="SELECT n FROM NumeroEncuesta n")
public class NumeroEncuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNumeroEncuesta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Encuesta
	@OneToMany(mappedBy="numeroencuesta")
	private List<Encuesta> encuestas;

	//bi-directional many-to-one association to Pregunta
	@OneToMany(mappedBy="numeroencuesta")
	private List<Pregunta> preguntas;

	public NumeroEncuesta() {
	}

	public int getIdNumeroEncuesta() {
		return this.idNumeroEncuesta;
	}

	public void setIdNumeroEncuesta(int idNumeroEncuesta) {
		this.idNumeroEncuesta = idNumeroEncuesta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Encuesta> getEncuestas() {
		return this.encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public Encuesta addEncuesta(Encuesta encuesta) {
		getEncuestas().add(encuesta);
		encuesta.setNumeroencuesta(this);

		return encuesta;
	}

	public Encuesta removeEncuesta(Encuesta encuesta) {
		getEncuestas().remove(encuesta);
		encuesta.setNumeroencuesta(null);

		return encuesta;
	}

	public List<Pregunta> getPreguntas() {
		return this.preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public Pregunta addPregunta(Pregunta pregunta) {
		getPreguntas().add(pregunta);
		pregunta.setNumeroencuesta(this);

		return pregunta;
	}

	public Pregunta removePregunta(Pregunta pregunta) {
		getPreguntas().remove(pregunta);
		pregunta.setNumeroencuesta(null);

		return pregunta;
	}

}