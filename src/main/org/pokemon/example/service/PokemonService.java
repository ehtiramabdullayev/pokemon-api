package org.pokemon.example.service;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class PokemonService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonRepo pokemonRepo;

    public PokemonService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public GenericResponse<List<PokemonEntity>> pokemonList() {
        logger.info("Getting the pokemons list from the DB");
        List<PokemonEntity> allPokemonList = pokemonRepo.getAllPokemonList();
        return new GenericResponse<>(Collections.unmodifiableList(allPokemonList));
    }

    public boolean storePokemon(Pokemon pokemon) {
        logger.info("Storing the pokemon {} to our DB ", pokemon);
        return pokemonRepo.savePokemon(String.valueOf(pokemon.getId()),
                new PokemonEntity(pokemon.getName(),
                        pokemon.getFirstType(),
                        pokemon.getSecondType(),
                        pokemon.getTotal(),
                        pokemon.getHp(),
                        pokemon.getAttack(),
                        pokemon.getDefense(),
                        pokemon.getSpAttack(),
                        pokemon.getSpDefense(),
                        pokemon.getSpeed(),
                        pokemon.getGeneration(),
                        pokemon.isLegendary()));
    }

}
