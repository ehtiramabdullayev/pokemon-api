package org.pokemon.example.controller;

import spark.Request;
import spark.Response;

public interface PokemonOperationsController {
    String getPokemonList(Request request, Response response);
}
