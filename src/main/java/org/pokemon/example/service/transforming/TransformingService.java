package org.pokemon.example.service.transforming;

public interface TransformingService<T> {
    T transform(T obj);
}
