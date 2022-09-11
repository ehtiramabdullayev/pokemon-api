package org.pokemon.example.controller;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.PokemonReadingService;
import org.pokemon.example.service.PokemonService;
import org.pokemon.example.service.transforming.PokemonTransformingService;
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
    private final PokemonTransformingService pokemonTransformingService;

    @Autowired
    public PokemonOperationsController(PokemonService pokemonService,
                                       PokemonReadingService pokemonReadingService,
                                       PokemonTransformingService pokemonTransformingService) {
        this.pokemonService = pokemonService;
        this.pokemonReadingService = pokemonReadingService;
        this.pokemonTransformingService = pokemonTransformingService;
    }

    @PostConstruct
    public void init() {
        logger.info("init - Started.");
        pokemonReadingService
                .getPokemonList()
                .stream()
                .map(pokemonTransformingService::transform)
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

    @RequestMapping(value = "/pokemonName/filter", method = RequestMethod.POST)
    public GenericResponse<PokemonEntity> filterPokemonByProperties(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }
}
