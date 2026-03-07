package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.enums.EstadoAsiento;
import com.ticketflow.repository.AsientoRepository;
import java.util.List;
import java.util.UUID;

public class AsientoServiceImpl implements IAsientoService {
    private final AsientoRepository asientoRepository;

    public AsientoServiceImpl(AsientoRepository asientoRepository) {
        this.asientoRepository = asientoRepository;
    }

    @Override
    public Asiento crear(Asiento asiento) {
        asientoRepository.save(asiento);
        return asiento;
    }

    @Override
    public void actualizar(Asiento asiento) {
        asientoRepository.update(asiento);
    }

    @Override
    public void eliminar(UUID idAsiento) {
        asientoRepository.delete(idAsiento);
    }

    @Override
    public List<Asiento> listarPorZona(UUID idZona) {
        return asientoRepository.findByZona(idZona);
    }

    @Override
    public void cambiarEstado(UUID idAsiento, EstadoAsiento estado) {
        asientoRepository.findById(idAsiento).ifPresent(a -> {
            a.setEstadoAsiento(estado);
            asientoRepository.update(a);
        });
    }

    @Override
    public List<Asiento> obtenerMapaDisponibilidad(UUID idZona) {
        return asientoRepository.findByZona(idZona);
    }

    @Override
    public void bloquear(UUID idAsiento) {
        cambiarEstado(idAsiento, EstadoAsiento.BLOQUEADO);
    }

    @Override
    public void liberar(UUID idAsiento) {
        cambiarEstado(idAsiento, EstadoAsiento.DISPONIBLE);
    }

    @Override
    public int obtenerOcupacion(UUID idZona) {
        return (int) asientoRepository.findByZona(idZona).stream()
                .filter(a -> a.getEstadoAsiento() == EstadoAsiento.VENDIDO
                        || a.getEstadoAsiento() == EstadoAsiento.RESERVADO)
                .count();
    }
}
