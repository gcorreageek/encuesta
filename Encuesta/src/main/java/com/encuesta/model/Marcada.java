package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the marcadas database table.
 * 
 */
@Entity
@Table(name="marcadas")
@NamedQuery(name="Marcada.findAll", query="SELECT m FROM Marcada m")
public class Marcada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMarcadas;

	@Column(name="tipo_respuesta_d")
	private int tipoRespuestaD;

	//bi-directional many-to-one association to Alternativa
	@ManyToOne
	@JoinColumn(name="idAlternativas")
	private Alternativa alternativa;

	//bi-directional many-to-one association to Encuestaalumno
	@ManyToOne
	@JoinColumn(name="idEncuestaAlumno")
	private EncuestaAlumno encuestaalumno;

	public Marcada() {
	}

	public int getIdMarcadas() {
		return this.idMarcadas;
	}

	public void setIdMarcadas(int idMarcadas) {
		this.idMarcadas = idMarcadas;
	}

	public int getTipoRespuestaD() {
		return this.tipoRespuestaD;
	}

	public void setTipoRespuestaD(int tipoRespuestaD) {
		this.tipoRespuestaD = tipoRespuestaD;
	}

	public Alternativa getAlternativa() {
		return this.alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public EncuestaAlumno getEncuestaalumno() {
		return this.encuestaalumno;
	}

	public void setEncuestaalumno(EncuestaAlumno encuestaalumno) {
		this.encuestaalumno = encuestaalumno;
	}

}