package com.ventas.abm.manager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUNTO_DE_VENTA")
public class PuntoDeVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PUNTO_DE_VENTA")
	private Long id;

	@Column(name = "NUMERO")
	private Integer numero;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "NUM_CAJA")
	private Short numeroDeCaja;

	@Column(name = "USUARIO")
	private String usuario;

	@ManyToOne
	@JoinColumn(name = "ID_TIENDA")
	private Tienda tienda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Short getNumeroDeCaja() {
		return numeroDeCaja;
	}

	public void setNumeroDeCaja(Short numeroDeCaja) {
		this.numeroDeCaja = numeroDeCaja;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

}
