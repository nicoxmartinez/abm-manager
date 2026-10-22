package com.ventas.abm.manager.domain;

public enum TipoMedioPago {
	EFECTIVO("Efectivo"),
	DEBITO("Tarjeta de Débito"), 
	CREDITO("Tarjeta de Crédito"), 
	PREPAGO("Tarjeta Prepaga"), 
	CHEQUE("Cheque"), 
	PAGO_DIGITAL("Pago Digital");

	private final String descripcion;

	TipoMedioPago(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}