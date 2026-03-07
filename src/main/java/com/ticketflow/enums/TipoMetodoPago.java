package com.ticketflow.enums;

public enum TipoMetodoPago {
    TARJETA_CREDITO("Tarjeta de Crédito"),
    TARJETA_DEBITO("Tarjeta de Débito"),
    PSE("PSE"),
    EFECTIVO("Efectivo");

    private final String descripcion;

    TipoMetodoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
