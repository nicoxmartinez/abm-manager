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
	
}
