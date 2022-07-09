package org.pokemon.example.service.transform.function;

public interface TransformingFunction<T> {
    boolean couldBeApplied(T obj);
    T apply(T obj);
}
