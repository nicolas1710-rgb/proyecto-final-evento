package com.ticketflow.service;

import com.ticketflow.model.Incidencia;
import com.ticketflow.enums.TipoIncidencia;
import com.ticketflow.repository.IncidenciaRepository;
import java.time.LocalDateTime;
import java.util.List;

public class IncidenciaServiceImpl implements IIncidenciaService {
    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaServiceImpl(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    @Override
    public void registrar(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
        System.out.println("[IncidenciaService] Incidencia registrada: " + incidencia.getTipo());
    }

    @Override
    public List<Incidencia> consultarPorFechaYTipo(LocalDateTime desde, LocalDateTime hasta, TipoIncidencia tipo) {
        return incidenciaRepository.findByFechaYTipo(desde, hasta, tipo);
    }

    @Override
    public List<Incidencia> listarTodas() {
        return incidenciaRepository.findAll();
    }
}
