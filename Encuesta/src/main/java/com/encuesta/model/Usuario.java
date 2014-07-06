package com.encuesta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u") 
@NamedQueries({  
	@NamedQuery(name="Usuario.findId", query="SELECT u FROM Usuario u WHERE u.idUsuario = ?1"),
	@NamedQuery(name="Usuario.findUsuario", query="SELECT u FROM Usuario u WHERE u.userName = ?1")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;

	@Column(name="apellido_materno")
	private String apellidoMaterno;

	@Column(name="apellido_paterno")
	private String apellidoPaterno;

	private String codigo;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_nac")
	private Date fechaNac;

	private String habilitado;

	private String nombre;

	@Column(name="pass_name")
	private String passName;

	private String telefono;

	@Column(name="tipo_usuario_d")
	private int tipoUsuarioD;

	@Column(name="user_name")
	private String userName;
	
	//bi-directional many-to-one association to Asignacionprofesor
	@OneToMany(mappedBy="usuario")
	private List<AsignacionProfesor> asignacionprofesors;

	//bi-directional many-to-one association to Encuestaalumno
	@OneToMany(mappedBy="usuario")
	private List<EncuestaAlumno> encuestaalumnos;

	//bi-directional many-to-one association to Encuestaprofesor
	@OneToMany(mappedBy="usuario")
	private List<EncuestaProfesor> encuestaprofesors;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="usuario")
	private List<Matricula> matriculas;

	//bi-directional many-to-one association to Profesocurso
	@OneToMany(mappedBy="usuario")
	private List<ProfesoCurso> profesocursos;


	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="idCargo")
	private Cargo cargoDelUsuario;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassName() {
		return this.passName;
	}

	public void setPassName(String passName) {
		this.passName = passName;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getTipoUsuarioD() {
		return this.tipoUsuarioD;
	}

	public void setTipoUsuarioD(int tipoUsuarioD) {
		this.tipoUsuarioD = tipoUsuarioD;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<AsignacionProfesor> getAsignacionprofesors() {
		return this.asignacionprofesors;
	}

	public void setAsignacionprofesors(List<AsignacionProfesor> asignacionprofesors) {
		this.asignacionprofesors = asignacionprofesors;
	}

	public AsignacionProfesor addAsignacionprofesor(AsignacionProfesor asignacionprofesor) {
		getAsignacionprofesors().add(asignacionprofesor);
		asignacionprofesor.setUsuario(this);

		return asignacionprofesor;
	}

	public AsignacionProfesor removeAsignacionprofesor(AsignacionProfesor asignacionprofesor) {
		getAsignacionprofesors().remove(asignacionprofesor);
		asignacionprofesor.setUsuario(null);

		return asignacionprofesor;
	}

	public List<EncuestaAlumno> getEncuestaalumnos() {
		return this.encuestaalumnos;
	}

	public void setEncuestaalumnos(List<EncuestaAlumno> encuestaalumnos) {
		this.encuestaalumnos = encuestaalumnos;
	}

	public EncuestaAlumno addEncuestaalumno(EncuestaAlumno encuestaalumno) {
		getEncuestaalumnos().add(encuestaalumno);
		encuestaalumno.setUsuario(this);

		return encuestaalumno;
	}

	public EncuestaAlumno removeEncuestaalumno(EncuestaAlumno encuestaalumno) {
		getEncuestaalumnos().remove(encuestaalumno);
		encuestaalumno.setUsuario(null);

		return encuestaalumno;
	}

	public List<EncuestaProfesor> getEncuestaprofesors() {
		return this.encuestaprofesors;
	}

	public void setEncuestaprofesors(List<EncuestaProfesor> encuestaprofesors) {
		this.encuestaprofesors = encuestaprofesors;
	}

	public EncuestaProfesor addEncuestaprofesor(EncuestaProfesor encuestaprofesor) {
		getEncuestaprofesors().add(encuestaprofesor);
		encuestaprofesor.setUsuario(this);

		return encuestaprofesor;
	}

	public EncuestaProfesor removeEncuestaprofesor(EncuestaProfesor encuestaprofesor) {
		getEncuestaprofesors().remove(encuestaprofesor);
		encuestaprofesor.setUsuario(null);

		return encuestaprofesor;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setUsuario(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setUsuario(null);

		return matricula;
	}

	public List<ProfesoCurso> getProfesocursos() {
		return this.profesocursos;
	}

	public void setProfesocursos(List<ProfesoCurso> profesocursos) {
		this.profesocursos = profesocursos;
	}

	public ProfesoCurso addProfesocurso(ProfesoCurso profesocurso) {
		getProfesocursos().add(profesocurso);
		profesocurso.setUsuario(this);

		return profesocurso;
	}

	public ProfesoCurso removeProfesocurso(ProfesoCurso profesocurso) {
		getProfesocursos().remove(profesocurso);
		profesocurso.setUsuario(null);

		return profesocurso;
	}

	public Cargo getCargoDelUsuario() {
//		if(cargoDelUsuario==null){
//			cargoDelUsuario = new Cargo();
//		}
		return cargoDelUsuario;
	}

	public void setCargoDelUsuario(Cargo cargoDelUsuario) {
		this.cargoDelUsuario = cargoDelUsuario;
	}

 

}