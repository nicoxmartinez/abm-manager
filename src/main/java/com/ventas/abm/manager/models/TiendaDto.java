package com.ventas.abm.manager.models;

public class TiendaDto {

	private Long idTienda;
	private String codigo;
	private String descripcion;
	private String tipoTienda;

	public TiendaDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTipoTienda() {
		return tipoTienda;
	}

	public void setTipoTienda(String tipoTienda) {
		this.tipoTienda = tipoTienda;
	}

}
