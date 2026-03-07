package com.ticketflow.service;

import com.ticketflow.model.Incidencia;
import com.ticketflow.enums.TipoIncidencia;
import java.time.LocalDateTime;
import java.util.List;

public interface IIncidenciaService {
    void registrar(Incidencia incidencia);

    List<Incidencia> consultarPorFechaYTipo(LocalDateTime desde, LocalDateTime hasta, TipoIncidencia tipo);

    List<Incidencia> listarTodas();
}
