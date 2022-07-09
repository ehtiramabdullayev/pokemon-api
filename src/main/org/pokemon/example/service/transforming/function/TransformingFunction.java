package org.pokemon.example.service.transforming.function;

public interface TransformingFunction<T> {
    boolean couldBeApplied(T obj);
    T apply(T obj);
}
