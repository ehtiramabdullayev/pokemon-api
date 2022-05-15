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
        pokemons.put(id, entity);
        return true;
    }

    @Override
    public List<PokemonEntity> getAllPokemonList() {
        return new LinkedList<>(pokemons.values());
    }
}
