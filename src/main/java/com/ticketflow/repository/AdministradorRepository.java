package com.ticketflow.repository;

import com.ticketflow.model.Administrador;
import java.util.*;

public class AdministradorRepository implements CrudRepository<Administrador, UUID> {
    private final Map<UUID, Administrador> store = new HashMap<>();

    @Override
    public void save(Administrador entity) {
        store.put(entity.getIdAdmin(), entity);
    }

    @Override
    public Optional<Administrador> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Administrador> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Administrador entity) {
        store.put(entity.getIdAdmin(), entity);
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    public Optional<Administrador> findByCorreo(String correo) {
        return store.values().stream()
                .filter(a -> a.getCorreoElectronico().equalsIgnoreCase(correo))
                .findFirst();
    }
}
