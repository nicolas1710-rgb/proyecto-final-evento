package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.enums.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IMetricasService {
    Map<LocalDate, Double> ventasPorPeriodo(LocalDate desde, LocalDate hasta);

    Map<Zona, Double> ocupacionPorZona(java.util.UUID idEvento);

    Map<TipoServicio, Double> ingresosPorServiciosAdicionales();

    double tasaCancelacion();

    List<Evento> topEventos(int n);
}
