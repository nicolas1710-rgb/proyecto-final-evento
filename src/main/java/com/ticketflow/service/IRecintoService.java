package com.ticketflow.service;

import com.ticketflow.model.*;
import java.util.List;
import java.util.UUID;

public interface IRecintoService {
    Recinto crear(Recinto recinto);

    void actualizar(Recinto recinto);

    void eliminar(UUID idRecinto);

    List<Recinto> listarRecintos();

    Recinto obtenerPorId(UUID idRecinto);

    void agregarZona(UUID idRecinto, Zona zona);

    void eliminarZona(UUID idRecinto, UUID idZona);
}
