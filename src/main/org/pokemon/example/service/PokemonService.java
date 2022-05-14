package org.pokemon.example.service;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;

import java.util.List;

public interface PokemonService {
    GenericResponse<List<PokemonEntity>> pokemonList();
}
