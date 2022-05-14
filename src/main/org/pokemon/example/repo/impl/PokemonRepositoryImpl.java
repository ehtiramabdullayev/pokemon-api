package org.pokemon.example.repo.impl;

import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PokemonRepositoryImpl implements PokemonRepo {
    private final Map<String, PokemonEntity> pokemons;

    public PokemonRepositoryImpl() {
        this.pokemons = new ConcurrentHashMap<>();

    }

    @Override
    public boolean savePokemon(String id, PokemonEntity entity) {
        validatePokemon(id);
        pokemons.put(id, entity);
        return true;
    }

    //TODO fix error handling
    private void validatePokemon(String id) {
        if (pokemons.containsKey(id)) {
            //TODO fix error handling
            throw new IllegalArgumentException("Pokemon with given ID already exists!");
        }
    }

    @Override
    public List<PokemonEntity> getAllPokemonList() {
        return new LinkedList<>(pokemons.values());
    }
}
