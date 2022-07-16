package org.pokemon.example.service;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.api.model.response.Response;
import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonRepo pokemonRepo;

    public PokemonService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public GenericResponse<List<PokemonEntity>> pokemonList() {
        logger.info("Getting the pokemons list from the DB");
        Optional<List<PokemonEntity>> allPokemonList = pokemonRepo.getAllPokemonList();
        return allPokemonList
                .map(pokemonEntities -> new GenericResponse<>(Collections.unmodifiableList(pokemonEntities)))
                .orElseGet(() -> new GenericResponse<>(Collections.emptyList()));
    }

    public GenericResponse<PokemonEntity> getPokemonByName(String name) {
        logger.info("Getting the pokemon [{}] from the DB by its name", name);
        Optional<PokemonEntity> pokemonOptional = pokemonRepo.getAllPokemonByName(name);
        GenericResponse<PokemonEntity> genericResponse = new GenericResponse<>(
                new Response(200, "Could not find the pokemon entity")
        );
        if (pokemonOptional.isPresent()) {
            PokemonEntity pokemonEntity = pokemonOptional.get();
            genericResponse = new GenericResponse<>(pokemonEntity);
        } else logger.error("Pokemon [{}] doesn't exist the DB", name);
        return genericResponse;
    }

    public void storePokemon(Pokemon pokemon) {
        logger.info("Storing the pokemon name [{}] to our DB ", pokemon.getName());
        if (pokemonRepo.savePokemon(String.valueOf(pokemon.getId()),
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
                        pokemon.isLegendary()))) logger.info("Pokemon [{}] stored to the DB ", pokemon);
        else logger.error("Failed to store pokemon [{}] to the DB", pokemon);
    }
}
