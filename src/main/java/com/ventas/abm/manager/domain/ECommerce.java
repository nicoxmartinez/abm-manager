package com.ventas.abm.manager.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ECOMMERCE")
@PrimaryKeyJoinColumn(name = "ID_TIENDA")
public class ECommerce extends Tienda implements Serializable {

	private static final long serialVersionUID = -1356565527033697815L;

	@Column(name = "DOMINIO_WEB")
	private String dominio;

	public ECommerce() {
		// TODO Auto-generated constructor stub
		super();
		this.setTipo(TipoTienda.VIRTUAL);
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
