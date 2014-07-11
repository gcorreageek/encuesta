package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the alternativas database table.
 * 
 */
@Entity
@Table(name="alternativas")
@NamedQuery(name="Alternativa.findAll", query="SELECT a FROM Alternativa a")
@NamedQueries({  
	@NamedQuery(name="Alternativa.findId", query="SELECT u FROM Alternativa u WHERE u.idAlternativas = ?1 "),
	@NamedQuery(name="Alternativa.findIdPregunta", query="SELECT u FROM Alternativa u WHERE u.pregunta.idPregunta = ?1   ")
})
public class Alternativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlternativas;

	private String alternativa;

	private int orden;

	private double porcentaje;

	private int puntaje;

	@Column(name="tipo_alternativa_d")
	private int tipoAlternativaD;

	@Column(name="tipo_respuesta_d")
	private int tipoRespuestaD;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne
	@JoinColumn(name="idPregunta")
	private Pregunta pregunta;

	//bi-directional many-to-one association to Marcada
	@OneToMany(mappedBy="alternativa")
	private List<Marcada> marcadas;

	public Alternativa() {
	}

	public int getIdAlternativas() {
		return this.idAlternativas;
	}

	public void setIdAlternativas(int idAlternativas) {
		this.idAlternativas = idAlternativas;
	}

	public String getAlternativa() {
		return this.alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getTipoAlternativaD() {
		return this.tipoAlternativaD;
	}

	public void setTipoAlternativaD(int tipoAlternativaD) {
		this.tipoAlternativaD = tipoAlternativaD;
	}

	public int getTipoRespuestaD() {
		return this.tipoRespuestaD;
	}

	public void setTipoRespuestaD(int tipoRespuestaD) {
		this.tipoRespuestaD = tipoRespuestaD;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public List<Marcada> getMarcadas() {
		return this.marcadas;
	}

	public void setMarcadas(List<Marcada> marcadas) {
		this.marcadas = marcadas;
	}

	public Marcada addMarcada(Marcada marcada) {
		getMarcadas().add(marcada);
		marcada.setAlternativa(this);

		return marcada;
	}

	public Marcada removeMarcada(Marcada marcada) {
		getMarcadas().remove(marcada);
		marcada.setAlternativa(null);

		return marcada;
	}

}