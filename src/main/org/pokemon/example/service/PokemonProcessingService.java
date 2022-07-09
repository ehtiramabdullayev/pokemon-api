package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.service.transforming.PokemonTransformingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonProcessingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonTransformingService pokemonTransformingService;

    public PokemonProcessingService(PokemonTransformingService pokemonTransformingService) {
        this.pokemonTransformingService = pokemonTransformingService;
    }

    public Pokemon processPokemon(Pokemon pokemon) {
        logger.info("Processing and storing the pokemon {}", pokemon);
        return pokemonTransformingService.transform(pokemon);
    }
}
