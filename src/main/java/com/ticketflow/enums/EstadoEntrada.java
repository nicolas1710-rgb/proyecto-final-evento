package com.ticketflow.enums;

public enum EstadoEntrada {
    ACTIVA("Activa"),
    USADA("Usada"),
    ANULADA("Anulada");

    private final String descripcion;

    EstadoEntrada(String descripcion) {
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
