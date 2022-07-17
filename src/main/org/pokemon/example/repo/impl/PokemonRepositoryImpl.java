package org.pokemon.example.repo.impl;

import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PokemonRepositoryImpl implements PokemonRepo {
    private final Map<String, PokemonEntity> pokemonEntityMap;

    public PokemonRepositoryImpl() {
        this.pokemonEntityMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean savePokemon(String id, PokemonEntity entity) {
            pokemonEntityMap.putIfAbsent(id, entity);
            return true;
    }

    @Override
    public Optional<List<PokemonEntity>> getAllPokemonList() {
        LinkedList<PokemonEntity> pokemonEntities = new LinkedList<>(pokemonEntityMap.values());
        return Optional.of(pokemonEntities);
    }

    @Override
    public Optional<PokemonEntity> getAllPokemonByName(String name) {
        return pokemonEntityMap.values()
                .stream()
                .filter(pokemonEntity -> pokemonEntity.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
