package org.pokemon.example.controller;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.BatchProcessingService;
import org.pokemon.example.service.JsonParsingService;
import org.pokemon.example.service.PokemonService;
import org.pokemon.example.service.impl.PokemonServiceImpl;
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
    private final JsonParsingService jsonParsingService;
    private final BatchProcessingService processingService;

    @Autowired
    public PokemonOperationsController(PokemonServiceImpl pokemonService,
                                       JsonParsingService jsonParsingService,
                                       BatchProcessingService processingService) {
        this.pokemonService = pokemonService;
        this.jsonParsingService = jsonParsingService;
        this.processingService = processingService;
    }

    @PostConstruct
    public void init() {
        logger.info("init - Started.");

        processingService.pokemons()
                .forEach( processingService::processPokemon );

        logger.info("init - Done.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public GenericResponse<List<PokemonEntity>>  getPokemonList() {
        return pokemonService.pokemonList();
    }
}
