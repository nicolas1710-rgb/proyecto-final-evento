package com.ticketflow.enums;

public enum TipoServicio {
    VIP_UPGRADE("VIP Upgrade"),
    SEGURO_CANCELACION("Seguro de Cancelación"),
    MERCHANDISING("Merchandising"),
    PARQUEADERO("Parqueadero"),
    ACCESO_PREFERENCIAL("Acceso Preferencial"),
    SEGURO("Seguro de Cancelación"),
    VIP("VIP Upgrade");

    private final String descripcion;

    TipoServicio(String descripcion) {
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
