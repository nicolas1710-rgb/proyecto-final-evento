package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.enums.EstadoCompra;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ICompraService {
    Compra crearCompra(UUID idUsuario, UUID idEvento, List<ItemCompra> items);

    Compra modificarCompra(UUID idCompra, List<ItemCompra> items, List<ServicioAdicional> servicios);

    boolean cancelarCompra(UUID idCompra);

    Pago confirmarPago(UUID idCompra, MetodoPago metodoPago);

    Compra obtenerDetalle(UUID idCompra);

    List<Compra> obtenerHistorial(UUID idUsuario, EstadoCompra filtroEstado);

    List<Compra> listarCompras(UUID idUsuario);

    boolean reasignarAsiento(UUID idCompra, UUID idEntrada, UUID idNuevoAsiento);

    List<Compra> listarTodas();
}
