package com.ticketflow.repository;

import com.ticketflow.model.Asiento;
import com.ticketflow.enums.EstadoAsiento;
import java.util.*;
import java.util.stream.Collectors;

public class AsientoRepository implements CrudRepository<Asiento, UUID> {
    private final Map<UUID, Asiento> store = new HashMap<>();

    @Override
    public void save(Asiento entity) {
        store.put(entity.getIdAsiento(), entity);
    }

    @Override
    public Optional<Asiento> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Asiento> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Asiento entity) {
        store.put(entity.getIdAsiento(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public List<Asiento> findByZona(UUID idZona) {
        return store.values().stream()
                .filter(a -> a.getZona() != null && a.getZona().getIdZona().equals(idZona))
                .collect(Collectors.toList());
    }

    public List<Asiento> findByZonaAndEstado(UUID idZona, EstadoAsiento estado) {
        return store.values().stream()
                .filter(a -> a.getZona() != null && a.getZona().getIdZona().equals(idZona))
                .filter(a -> a.getEstadoAsiento() == estado)
                .collect(Collectors.toList());
    }
}
