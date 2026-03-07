package com.ticketflow.enums;

public enum EstadoCompra {
    CREADA("Creada"),
    PAGADA("Pagada"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    REEMBOLSADA("Reembolsada"),
    INCIDENCIA("Incidencia");

    private final String descripcion;

    EstadoCompra(String descripcion) {
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
