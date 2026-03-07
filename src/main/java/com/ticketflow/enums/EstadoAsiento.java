package com.ticketflow.enums;

public enum EstadoAsiento {
    DISPONIBLE("Disponible"),
    RESERVADO("Reservado"),
    VENDIDO("Vendido"),
    BLOQUEADO("Bloqueado"),
    PENDIENTE_PAGO("Pendiente de Pago"),
    MANTENIMIENTO("Mantenimiento");

    private final String descripcion;

    EstadoAsiento(String descripcion) {
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
