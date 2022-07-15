package org.pokemon.example.repo;

import org.pokemon.example.model.PokemonEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepo {
    boolean savePokemon(String id, PokemonEntity entity);
    List<PokemonEntity> getAllPokemonList();
    Optional<PokemonEntity> getAllPokemonByName(String name);
}
