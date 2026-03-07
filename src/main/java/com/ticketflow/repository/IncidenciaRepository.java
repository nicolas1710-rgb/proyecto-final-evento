package com.ticketflow.repository;

import com.ticketflow.model.Incidencia;
import com.ticketflow.enums.TipoIncidencia;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class IncidenciaRepository implements CrudRepository<Incidencia, UUID> {
    private final Map<UUID, Incidencia> store = new HashMap<>();

    @Override
    public void save(Incidencia entity) {
        store.put(entity.getIdIncidencia(), entity);
    }

    @Override
    public Optional<Incidencia> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Incidencia> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Incidencia entity) {
        store.put(entity.getIdIncidencia(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public List<Incidencia> findByFechaYTipo(LocalDateTime desde, LocalDateTime hasta, TipoIncidencia tipo) {
        return store.values().stream()
                .filter(i -> (desde == null || !i.getFecha().isBefore(desde)))
                .filter(i -> (hasta == null || !i.getFecha().isAfter(hasta)))
                .filter(i -> tipo == null || i.getTipo() == tipo)
                .collect(Collectors.toList());
    }
}
