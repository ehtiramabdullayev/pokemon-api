package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.pokemon.example.service.transform.PokemonTransformingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonProcessingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonRepo pokemonRepo;
    private final PokemonTransformingService pokemonTransformingService;

    public PokemonProcessingService(PokemonRepo pokemonRepo,
                                    PokemonTransformingService pokemonTransformingService) {
        this.pokemonRepo = pokemonRepo;
        this.pokemonTransformingService = pokemonTransformingService;
    }

    public void processAndStorePokemon(Pokemon pokemon) {
        logger.info("Processing and storing the pokemon {}", pokemon);
        // this point we transform the pokemons based on their types
        Pokemon processedPokemon = pokemonTransformingService.transform(pokemon);
        // saving the pokemon to our "database"
        pokemonRepo.savePokemon(String.valueOf(processedPokemon.getId()),
                new PokemonEntity(processedPokemon.getName(),
                        processedPokemon.getFirstType(),
                        pokemon.getSecondType(),
                        processedPokemon.getTotal(),
                        processedPokemon.getHp(),
                        processedPokemon.getAttack(),
                        processedPokemon.getDefense(),
                        processedPokemon.getSpAttack(),
                        processedPokemon.getSpDefense(),
                        processedPokemon.getSpeed(),
                        processedPokemon.getGeneration(),
                        processedPokemon.isLegendary()));
    }
}
