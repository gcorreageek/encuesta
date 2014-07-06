package com.encuesta.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMenu;

	@Column(name="icono_menu")
	private String iconoMenu;

	@Column(name="id_submenu")
	private int idSubmenu;

	@Column(name="master_menu")
	private int masterMenu;

	@Column(name="nom_menu")
	private String nomMenu;

	@Column(name="orden_menu")
	private int ordenMenu;

	@Column(name="tipo_menu")
	private int tipoMenu;

	@Column(name="url_menu")
	private String urlMenu;

	//bi-directional many-to-one association to Acceso
	@OneToMany(mappedBy="menu")
	private List<Acceso> accesos;

	public Menu() {
	}

	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getIconoMenu() {
		return this.iconoMenu;
	}

	public void setIconoMenu(String iconoMenu) {
		this.iconoMenu = iconoMenu;
	}

	public int getIdSubmenu() {
		return this.idSubmenu;
	}

	public void setIdSubmenu(int idSubmenu) {
		this.idSubmenu = idSubmenu;
	}

	public int getMasterMenu() {
		return this.masterMenu;
	}

	public void setMasterMenu(int masterMenu) {
		this.masterMenu = masterMenu;
	}

	public String getNomMenu() {
		return this.nomMenu;
	}

	public void setNomMenu(String nomMenu) {
		this.nomMenu = nomMenu;
	}

	public int getOrdenMenu() {
		return this.ordenMenu;
	}

	public void setOrdenMenu(int ordenMenu) {
		this.ordenMenu = ordenMenu;
	}

	public int getTipoMenu() {
		return this.tipoMenu;
	}

	public void setTipoMenu(int tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

	public String getUrlMenu() {
		return this.urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public List<Acceso> getAccesos() {
		return this.accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public Acceso addAcceso(Acceso acceso) {
		getAccesos().add(acceso);
		acceso.setMenu(this);

		return acceso;
	}

	public Acceso removeAcceso(Acceso acceso) {
		getAccesos().remove(acceso);
		acceso.setMenu(null);

		return acceso;
	}

}