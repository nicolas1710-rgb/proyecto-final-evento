package com.ticketflow.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones CRUD en memoria.
 *
 * @param <T>  tipo de la entidad
 * @param <ID> tipo del identificador
 */
public interface CrudRepository<T, ID> {
    void save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void update(T entity);

    void delete(ID id);
}
