package com.ticketflow.repository;

import com.ticketflow.model.Zona;
import java.util.*;

public class ZonaRepository implements CrudRepository<Zona, UUID> {
    private final Map<UUID, Zona> store = new HashMap<>();

    @Override
    public void save(Zona entity) {
        store.put(entity.getIdZona(), entity);
    }

    @Override
    public Optional<Zona> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Zona> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Zona entity) {
        store.put(entity.getIdZona(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }
}
