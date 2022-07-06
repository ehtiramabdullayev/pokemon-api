package org.pokemon.example.service.transformation.function;

public interface TransformationFunction<T> {
    boolean couldBeApplied(T obj);
    T apply(T obj);
}
