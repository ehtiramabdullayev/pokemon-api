package org.pokemon.example.controller;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.PokemonProcessingService;
import org.pokemon.example.service.PokemonReaderService;
import org.pokemon.example.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonOperationsController {
    Logger logger = LoggerFactory.getLogger(PokemonOperationsController.class);

    private final PokemonService pokemonService;
    private final PokemonReaderService pokemonReaderService;
    private final PokemonProcessingService pokemonProcessingService;

    @Autowired
    public PokemonOperationsController(PokemonService pokemonService,
                                       PokemonReaderService pokemonReaderService,
                                       PokemonProcessingService pokemonProcessingService) {
        this.pokemonService = pokemonService;
        this.pokemonReaderService = pokemonReaderService;
        this.pokemonProcessingService = pokemonProcessingService;
    }

    @PostConstruct
    public void init() {
        logger.info("init - Started.");

        pokemonReaderService.pokemons()
                .forEach(pokemonProcessingService::processPokemon);

        logger.info("init - Done.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public GenericResponse<List<PokemonEntity>> getPokemonList() {
        return pokemonService.pokemonList();
    }
}
