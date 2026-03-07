package com.ticketflow.repository;

import com.ticketflow.model.Recinto;
import java.util.*;

public class RecintoRepository implements CrudRepository<Recinto, UUID> {
    private final Map<UUID, Recinto> store = new HashMap<>();

    @Override
    public void save(Recinto entity) {
        store.put(entity.getIdRecinto(), entity);
    }

    @Override
    public Optional<Recinto> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Recinto> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Recinto entity) {
        store.put(entity.getIdRecinto(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public List<Recinto> findByCiudad(String ciudad) {
        return store.values().stream()
                .filter(r -> r.getCiudad().equalsIgnoreCase(ciudad))
                .toList();
    }
}
