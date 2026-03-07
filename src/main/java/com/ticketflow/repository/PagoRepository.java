package com.ticketflow.repository;

import com.ticketflow.model.Pago;
import java.util.*;

public class PagoRepository implements CrudRepository<Pago, UUID> {
    private final Map<UUID, Pago> store = new HashMap<>();

    @Override
    public void save(Pago entity) {
        store.put(entity.getIdPago(), entity);
    }

    @Override
    public Optional<Pago> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Pago> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Pago entity) {
        store.put(entity.getIdPago(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }
}
