package com.ticketflow.repository;

import com.ticketflow.model.Compra;
import com.ticketflow.enums.EstadoCompra;
import java.util.*;
import java.util.stream.Collectors;

public class CompraRepository implements CrudRepository<Compra, UUID> {
    private final Map<UUID, Compra> store = new HashMap<>();

    @Override
    public void save(Compra entity) {
        store.put(entity.getIdCompra(), entity);
    }

    @Override
    public Optional<Compra> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Compra> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Compra entity) {
        store.put(entity.getIdCompra(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public List<Compra> findByUsuario(UUID idUsuario) {
        return store.values().stream()
                .filter(c -> c.getUsuario() != null && c.getUsuario().getIdUsuario().equals(idUsuario))
                .collect(Collectors.toList());
    }

    public List<Compra> findByEvento(UUID idEvento) {
        return store.values().stream()
                .filter(c -> c.getEvento() != null && c.getEvento().getIdEvento().equals(idEvento))
                .collect(Collectors.toList());
    }

    public List<Compra> findByEstado(EstadoCompra estado) {
        return store.values().stream()
                .filter(c -> c.getEstadoCompra() == estado)
                .collect(Collectors.toList());
    }
}
