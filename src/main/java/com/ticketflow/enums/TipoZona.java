package com.ticketflow.enums;

public enum TipoZona {
    VIP("VIP"),
    PREFERENCIAL("Preferencial"),
    GENERAL("General");

    private final String descripcion;

    TipoZona(String descripcion) {
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
