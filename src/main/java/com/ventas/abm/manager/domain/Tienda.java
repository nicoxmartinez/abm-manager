package com.ventas.abm.manager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIENDA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Tienda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIENDA")
	private Long idTienda;

	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoTienda tipo;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "TELEFONO")
	private String telefono;

	@OneToMany(mappedBy = "tienda")
	@OrderBy(value = "ID_ALMACEN")
	@JsonIgnore
	private List<Almacen> almacenes;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;

	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tienda(Long idTienda, String codigo, String descripcion, TipoTienda tipo, String usuario, String email,
			String telefono) {
		super();
		this.idTienda = idTienda;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.usuario = usuario;
		this.email = email;
		this.telefono = telefono;
	}

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoTienda getTipo() {
		return tipo;
	}

	public void setTipo(TipoTienda tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
