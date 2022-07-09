package org.pokemon.example.service.transform;

public interface TransformationService<T> {
    T transform(T obj);
}
