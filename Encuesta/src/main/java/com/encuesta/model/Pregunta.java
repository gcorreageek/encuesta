package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the pregunta database table.
 * 
 */
@Entity
@NamedQuery(name="Pregunta.findAll", query="SELECT p FROM Pregunta p")
@NamedQueries({  
	@NamedQuery(name="Pregunta.findId", query="SELECT u FROM Pregunta u WHERE u.idPregunta = ?1 "),
	@NamedQuery(name="Pregunta.findIdNumeroEncuesta", query="SELECT u FROM Pregunta u WHERE u.numeroencuesta.idNumeroEncuesta = ?1   ")
})
public class Pregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPregunta;

	private int orden;

	private String pregunta;

	@Column(name="pregunta_obligatoria_d")
	private int preguntaObligatoriaD;

	private int puntaje;

	@Column(name="tipo_pregunta_d")
	private int tipoPreguntaD;

	//bi-directional many-to-one association to Alternativa
	@OneToMany(mappedBy="pregunta")
	private List<Alternativa> alternativas;

	//bi-directional many-to-one association to Numeroencuesta
	@ManyToOne
	@JoinColumn(name="idNumeroEncuesta")
	private NumeroEncuesta numeroencuesta;

	public Pregunta() {
	}

	public int getIdPregunta() {
		return this.idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public int getPreguntaObligatoriaD() {
		return this.preguntaObligatoriaD;
	}

	public void setPreguntaObligatoriaD(int preguntaObligatoriaD) {
		this.preguntaObligatoriaD = preguntaObligatoriaD;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getTipoPreguntaD() {
		return this.tipoPreguntaD;
	}

	public void setTipoPreguntaD(int tipoPreguntaD) {
		this.tipoPreguntaD = tipoPreguntaD;
	}

	public List<Alternativa> getAlternativas() {
		if(this.alternativas==null){
			this.alternativas = new ArrayList<Alternativa>();
		}
		return this.alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public Alternativa addAlternativa(Alternativa alternativa) {
		getAlternativas().add(alternativa);
		alternativa.setPregunta(this);

		return alternativa;
	}

	public Alternativa removeAlternativa(Alternativa alternativa) {
		getAlternativas().remove(alternativa);
		alternativa.setPregunta(null);

		return alternativa;
	}

	public NumeroEncuesta getNumeroencuesta() {
		return this.numeroencuesta;
	}

	public void setNumeroencuesta(NumeroEncuesta numeroencuesta) {
		this.numeroencuesta = numeroencuesta;
	}

}