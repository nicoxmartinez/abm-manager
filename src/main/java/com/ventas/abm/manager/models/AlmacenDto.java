package com.ventas.abm.manager.models;

import java.util.Date;

public class AlmacenDto {

	private Long idAlmacen;
	private String codigo;
	private String descripcion;
	private TiendaDto tienda;
	private String direccion;
	private Date fechaBaja;

	public AlmacenDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(Long idAlmacen) {
		this.idAlmacen = idAlmacen;
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

	public TiendaDto getTienda() {
		return tienda;
	}

	public void setTienda(TiendaDto tienda) {
		this.tienda = tienda;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

}
