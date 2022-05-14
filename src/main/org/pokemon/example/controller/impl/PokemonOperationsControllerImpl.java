package org.pokemon.example.controller.impl;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.controller.PokemonOperationsController;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.pokemon.example.repo.impl.PokemonRepositoryImpl;
import org.pokemon.example.service.JsonParsingService;
import org.pokemon.example.service.PokemonService;
import org.pokemon.example.service.impl.JsonParsingServiceImpl;
import org.pokemon.example.service.impl.PokemonServiceImpl;
import spark.Request;
import spark.Response;

import java.util.List;

public class PokemonOperationsControllerImpl implements PokemonOperationsController {
    private final PokemonService pokemonService;

    public PokemonOperationsControllerImpl(PokemonServiceImpl pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Override
    public String getPokemonList(Request request, Response response) {
        JsonParsingService jsonParsingService = new JsonParsingServiceImpl();
        GenericResponse<List<PokemonEntity>> listGenericResponse = pokemonService.pokemonList();
        response.status(listGenericResponse.getResponse().getStatus());
        return jsonParsingService.toJsonPOJO(listGenericResponse);
    }
}
