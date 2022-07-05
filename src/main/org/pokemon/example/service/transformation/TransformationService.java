package org.pokemon.example.service.transformation;

public interface TransformationService<T> {
    T transform(T obj);
}
