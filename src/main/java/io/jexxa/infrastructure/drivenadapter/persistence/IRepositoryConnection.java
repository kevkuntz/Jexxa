package io.jexxa.infrastructure.drivenadapter.persistence;

import java.util.List;
import java.util.Optional;

/**
 * Throws IllegalArgumentException if any operations fails
 */
@SuppressWarnings("unused")
public interface IRepositoryConnection<T, K>
{
    @SuppressWarnings("EmptyMethod")
    void update(T aggregate);

    void remove(K key);

    void removeAll();

    void add(T aggregate);

    Optional<T> get(K primaryKey);

    List<T> get();
}