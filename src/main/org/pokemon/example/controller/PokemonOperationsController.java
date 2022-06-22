package org.pokemon.example.controller;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.service.JsonParsingService;
import org.pokemon.example.service.PokemonService;
import org.pokemon.example.service.impl.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonOperationsController {

    private final PokemonService pokemonService;
    private final JsonParsingService jsonParsingService;

    @Autowired
    public PokemonOperationsController(PokemonServiceImpl pokemonService,
                                       JsonParsingService jsonParsingService) {
        this.pokemonService = pokemonService;
        this.jsonParsingService = jsonParsingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPokemonList() {
        GenericResponse<List<PokemonEntity>> listGenericResponse = pokemonService.pokemonList();
        return jsonParsingService.toJsonPOJO(listGenericResponse);
    }
}
