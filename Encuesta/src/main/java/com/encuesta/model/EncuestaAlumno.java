package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the encuestaalumno database table.
 * 
 */
@Entity
@NamedQuery(name="Encuestaalumno.findAll", query="SELECT e FROM EncuestaAlumno e")
@NamedQueries({  
	@NamedQuery(name="Encuestaalumno.findId", query="SELECT u FROM EncuestaAlumno u WHERE u.idEncuestaAlumno = ?1 "),//resolvio_d in(0,1)
	@NamedQuery(name="Encuestaalumno.findIdEncuestaProfesor", query="SELECT u FROM EncuestaAlumno u WHERE u.encuestaprofesor.idEncuestaProfesor = ?1 "),
	@NamedQuery(name="Encuestaalumno.findIdUsuario", query="SELECT u FROM EncuestaAlumno u WHERE u.usuario.idUsuario = ?1 and u.resolvioD in(0,1) ")


})
public class EncuestaAlumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEncuestaAlumno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fin_encuesta")
	private Date finEncuesta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inicio_encuesta")
	private Date inicioEncuesta;

	@Column(name="resolvio_d")
	private int resolvioD;
	
	
	@Column(name="numero_alumnos_validos")
	private String numeroAlumnosValidos;

	//bi-directional many-to-one association to Encuestaprofesor
	@ManyToOne
	@JoinColumn(name="idEncuestaProfesor")
	private EncuestaProfesor encuestaprofesor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Usuario usuario;

	//bi-directional many-to-one association to Marcada
	@OneToMany(mappedBy="encuestaalumno")
	private List<Marcada> marcadas;

	public EncuestaAlumno() {
	}

	public int getIdEncuestaAlumno() {
		return this.idEncuestaAlumno;
	}

	public void setIdEncuestaAlumno(int idEncuestaAlumno) {
		this.idEncuestaAlumno = idEncuestaAlumno;
	}

	public Date getFinEncuesta() {
		return this.finEncuesta;
	}

	public void setFinEncuesta(Date finEncuesta) {
		this.finEncuesta = finEncuesta;
	}

	public Date getInicioEncuesta() {
		return this.inicioEncuesta;
	}

	public void setInicioEncuesta(Date inicioEncuesta) {
		this.inicioEncuesta = inicioEncuesta;
	}

	public int getResolvioD() {
		return this.resolvioD;
	}

	public void setResolvioD(int resolvioD) {
		this.resolvioD = resolvioD;
	}

	public EncuestaProfesor getEncuestaprofesor() {
		return this.encuestaprofesor;
	}

	public void setEncuestaprofesor(EncuestaProfesor encuestaprofesor) {
		this.encuestaprofesor = encuestaprofesor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Marcada> getMarcadas() {
		return this.marcadas;
	}

	public void setMarcadas(List<Marcada> marcadas) {
		this.marcadas = marcadas;
	}

	public Marcada addMarcada(Marcada marcada) {
		getMarcadas().add(marcada);
		marcada.setEncuestaalumno(this);

		return marcada;
	}

	public Marcada removeMarcada(Marcada marcada) {
		getMarcadas().remove(marcada);
		marcada.setEncuestaalumno(null);

		return marcada;
	}

	public String getNumeroAlumnosValidos() {
		return numeroAlumnosValidos;
	}

	public void setNumeroAlumnosValidos(String numeroAlumnosValidos) {
		this.numeroAlumnosValidos = numeroAlumnosValidos;
	}

}