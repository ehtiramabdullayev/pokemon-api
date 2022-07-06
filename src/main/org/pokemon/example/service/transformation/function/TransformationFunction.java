package org.pokemon.example.service.transformation.function;

/**
 * Interface for providing extendable way of adding new transformations
 * @param <T>
 */
public interface TransformationFunction<T> {
    boolean couldBeApplied(T obj);
    T apply(T obj);
}
