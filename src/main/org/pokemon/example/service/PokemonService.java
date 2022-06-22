package org.pokemon.example.service;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonService {
    GenericResponse<List<PokemonEntity>> pokemonList();
}
