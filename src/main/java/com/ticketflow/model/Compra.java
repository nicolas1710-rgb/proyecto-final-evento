package com.ticketflow.model;

import com.ticketflow.enums.EstadoCompra;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representa una compra realizada por un usuario en la plataforma.
 * Agrupa ítems, servicios adicionales y el pago asociado.
 */
public class Compra {
    private UUID idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private List<ItemCompra> items;
    private List<ServicioAdicional> serviciosAdicionales;
    private Pago pago;

    public Compra() {
        this.idCompra = UUID.randomUUID();
        this.fechaCreacion = LocalDateTime.now();
        this.estadoCompra = EstadoCompra.CREADA;
        this.items = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
    }

    public Compra(Usuario usuario, Evento evento) {
        this();
        this.usuario = usuario;
        this.evento = evento;
    }

    public void calcularTotal() {
        double totalItems = items.stream().mapToDouble(ItemCompra::getSubtotal).sum();
        double totalServicios = serviciosAdicionales.stream().mapToDouble(ServicioAdicional::getPrecio).sum();
        this.total = totalItems + totalServicios;
    }

    public UUID getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(UUID idCompra) {
        this.idCompra = idCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public List<ItemCompra> getItems() {
        return items;
    }

    public void setItems(List<ItemCompra> items) {
        this.items = items;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Compra{id=" + idCompra + ", usuario=" + (usuario != null ? usuario.getNombreCompleto() : "N/A") +
                ", evento=" + (evento != null ? evento.getNombre() : "N/A") +
                ", total=" + total + ", estado=" + estadoCompra + "}";
    }
}
