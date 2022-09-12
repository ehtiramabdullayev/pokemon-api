package org.pokemon.example.repo;

import org.pokemon.example.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepo extends JpaRepository<PokemonEntity, String>, JpaSpecificationExecutor<PokemonEntity> {
    PokemonEntity getPokemonEntityByName(String name);
}
