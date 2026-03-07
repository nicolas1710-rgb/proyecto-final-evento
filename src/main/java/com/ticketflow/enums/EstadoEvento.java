package com.ticketflow.enums;

public enum EstadoEvento {
    BORRADOR("Borrador"),
    PUBLICADO("Publicado"),
    PAUSADO("Pausado"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private final String descripcion;

    EstadoEvento(String descripcion) {
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
