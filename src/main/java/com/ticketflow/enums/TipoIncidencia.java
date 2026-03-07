package com.ticketflow.enums;

public enum TipoIncidencia {
    DOBLE_COMPRA("Doble Compra"),
    ERROR_PAGO("Error de Pago"),
    CANCELACION_MASIVA("Cancelación Masiva"),
    ASIENTO_INVALIDO("Asiento Inválido"),
    OTRO("Otro");

    private final String descripcion;

    TipoIncidencia(String descripcion) {
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
