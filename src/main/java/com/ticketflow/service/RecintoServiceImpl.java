package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.repository.RecintoRepository;
import java.util.List;
import java.util.UUID;

public class RecintoServiceImpl implements IRecintoService {
    private final RecintoRepository recintoRepository;

    public RecintoServiceImpl(RecintoRepository recintoRepository) {
        this.recintoRepository = recintoRepository;
    }

    @Override
    public Recinto crear(Recinto recinto) {
        recintoRepository.save(recinto);
        return recinto;
    }

    @Override
    public void actualizar(Recinto recinto) {
        recintoRepository.update(recinto);
    }

    @Override
    public void eliminar(UUID idRecinto) {
        recintoRepository.delete(idRecinto);
    }

    @Override
    public List<Recinto> listarRecintos() {
        return recintoRepository.findAll();
    }

    @Override
    public Recinto obtenerPorId(UUID idRecinto) {
        return recintoRepository.findById(idRecinto).orElse(null);
    }

    @Override
    public void agregarZona(UUID idRecinto, Zona zona) {
        recintoRepository.findById(idRecinto).ifPresent(r -> {
            r.getZonas().add(zona);
            recintoRepository.update(r);
        });
    }

    @Override
    public void eliminarZona(UUID idRecinto, UUID idZona) {
        recintoRepository.findById(idRecinto).ifPresent(r -> {
            r.getZonas().removeIf(z -> z.getIdZona().equals(idZona));
            recintoRepository.update(r);
        });
    }
}
