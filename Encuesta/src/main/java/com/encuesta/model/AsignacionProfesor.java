package com.encuesta.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the asignacionprofesor database table.
 * 
 */
@Entity
@NamedQuery(name="Asignacionprofesor.findAll", query="SELECT a FROM AsignacionProfesor a")
@NamedQueries({  
	@NamedQuery(name="Asignacionprofesor.findId", query="SELECT u FROM AsignacionProfesor u WHERE u.idAsignacionProfesor = ?1 "),
	@NamedQuery(name="Asignacionprofesor.findIdAnioIdProfesor", query="SELECT u FROM AsignacionProfesor u WHERE u.anio.idAnio = ?1 and u.usuario.idUsuario = ?2 ")
})
public class AsignacionProfesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAsignacionProfesor;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Anio
	@ManyToOne
	@JoinColumn(name="idAnio")
	private Anio anio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idProfesor")
	private Usuario usuario;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="asignacionprofesor")
	private List<Matricula> matriculas;

	public AsignacionProfesor() {
	}

	public int getIdAsignacionProfesor() {
		return this.idAsignacionProfesor;
	}

	public void setIdAsignacionProfesor(int idAsignacionProfesor) {
		this.idAsignacionProfesor = idAsignacionProfesor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Anio getAnio() {
		return this.anio;
	}

	public void setAnio(Anio anio) {
		this.anio = anio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setAsignacionprofesor(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setAsignacionprofesor(null);

		return matricula;
	}

}