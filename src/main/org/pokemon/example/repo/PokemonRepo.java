package org.pokemon.example.repo;

import org.pokemon.example.model.PokemonEntity;

import java.util.List;

public interface PokemonRepo {
    boolean savePokemon(String id, PokemonEntity entity);

    List<PokemonEntity> getAllPokemonList();
}
