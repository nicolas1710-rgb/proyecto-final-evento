package com.ticketflow.service;

import com.ticketflow.model.*;
import java.util.List;
import java.util.UUID;

public interface IEntradaService {
    List<Entrada> generarEntradas(Compra compra);

    void anularEntrada(UUID idEntrada);

    List<Entrada> obtenerPorCompra(UUID idCompra);

    List<Entrada> obtenerPorEvento(UUID idEvento);
}
