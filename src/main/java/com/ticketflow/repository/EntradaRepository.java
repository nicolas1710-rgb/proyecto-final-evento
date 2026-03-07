package com.ticketflow.repository;

import com.ticketflow.model.Entrada;
import java.util.*;
import java.util.stream.Collectors;

public class EntradaRepository implements CrudRepository<Entrada, UUID> {
    private final Map<UUID, Entrada> store = new HashMap<>();

    @Override
    public void save(Entrada entity) {
        store.put(entity.getIdEntrada(), entity);
    }

    @Override
    public Optional<Entrada> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Entrada> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Entrada entity) {
        store.put(entity.getIdEntrada(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public List<Entrada> findByCompra(UUID idCompra) {
        return store.values().stream()
                .filter(e -> e.getCompra() != null && e.getCompra().getIdCompra().equals(idCompra))
                .collect(Collectors.toList());
    }

    public List<Entrada> findByEvento(UUID idEvento) {
        return store.values().stream()
                .filter(e -> e.getCompra() != null && e.getCompra().getEvento() != null
                        && e.getCompra().getEvento().getIdEvento().equals(idEvento))
                .collect(Collectors.toList());
    }
}
