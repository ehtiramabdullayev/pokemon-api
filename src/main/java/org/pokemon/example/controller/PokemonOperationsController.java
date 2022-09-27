package org.pokemon.example.controller;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.PokemonReadingService;
import org.pokemon.example.service.PokemonService;
import org.pokemon.example.service.transforming.PokemonTransformingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/pokemons")
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

    @GetMapping
    public GenericResponse<List<PokemonEntity>> getPokemonList() {
        return pokemonService.pokemonList();
    }

    @GetMapping(value = "/pokemonName/{name}")
    public GenericResponse<PokemonEntity> getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @GetMapping(value = "pokemon")
    public GenericResponse<List<PokemonEntity>> filterPokemonByProperties(@And({
            @Spec(path = "hp", params = "hp[gte]", spec = GreaterThanOrEqual.class),
            @Spec(path = "hp", params = "hp[lte]", spec = LessThanOrEqual.class),
            @Spec(path = "attack", params = "attack[gte]", spec = GreaterThanOrEqual.class),
            @Spec(path = "attack", params = "attack[lte]", spec = LessThanOrEqual.class),
            @Spec(path = "defense", params = "defense[gte]", spec = GreaterThanOrEqual.class),
            @Spec(path = "defense", params = "defense[lte]", spec = LessThanOrEqual.class),
            @Spec(path = "firstType", params = "firstType", spec = Equal.class),
            @Spec(path = "name", params = "name", spec = Equal.class)
    }) Specification<PokemonEntity> spec, Pageable page) {
        logger.info("Filtering pokemon entities by the given filters");
        return pokemonService.getPokemonByFilters(spec, page);
    }
}
