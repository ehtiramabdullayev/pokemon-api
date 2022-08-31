package org.pokemon.example.controller;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.PokemonProcessingService;
import org.pokemon.example.service.PokemonReadingService;
import org.pokemon.example.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/pokemon")
public class PokemonOperationsController {
    private final Logger logger = LoggerFactory.getLogger(PokemonOperationsController.class);
    private final PokemonService pokemonService;
    private final PokemonReadingService pokemonReadingService;
    private final PokemonProcessingService pokemonProcessingService;

    @Autowired
    public PokemonOperationsController(PokemonService pokemonService,
                                       PokemonReadingService pokemonReadingService,
                                       PokemonProcessingService pokemonProcessingService) {
        this.pokemonService = pokemonService;
        this.pokemonReadingService = pokemonReadingService;
        this.pokemonProcessingService = pokemonProcessingService;
    }

    @PostConstruct
    public void init() {
        logger.info("init - Started.");
        pokemonReadingService
                .readPokemons()
                .map(pokemonProcessingService::processPokemon)
                .forEach(pokemonService::storePokemon);
        logger.info("init - Done.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public GenericResponse<List<PokemonEntity>> getPokemonList() {
        return pokemonService.pokemonList();
    }

    @RequestMapping(value = "/pokemonName/{name}", method = RequestMethod.GET)
    public GenericResponse<PokemonEntity> getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }
}
