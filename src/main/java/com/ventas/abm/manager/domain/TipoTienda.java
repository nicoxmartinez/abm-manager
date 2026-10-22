package com.ventas.abm.manager.domain;

public enum TipoTienda {
	FISICA("Tienda Física"), VIRTUAL("Tienda Virtual");

	private final String descripcion;

	TipoTienda(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}