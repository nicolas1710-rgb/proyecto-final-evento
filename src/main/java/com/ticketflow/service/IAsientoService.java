package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.enums.EstadoAsiento;
import java.util.List;
import java.util.UUID;

public interface IAsientoService {
    Asiento crear(Asiento asiento);

    void actualizar(Asiento asiento);

    void eliminar(UUID idAsiento);

    List<Asiento> listarPorZona(UUID idZona);

    void cambiarEstado(UUID idAsiento, EstadoAsiento estado);

    List<Asiento> obtenerMapaDisponibilidad(UUID idZona);

    void bloquear(UUID idAsiento);

    void liberar(UUID idAsiento);

    int obtenerOcupacion(UUID idZona);
}
