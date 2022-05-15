package org.pokemon.example.service.impl;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.pokemon.example.service.PokemonService;

import java.util.Collections;
import java.util.List;

public class PokemonServiceImpl implements PokemonService {
    private final PokemonRepo pokemonRepo;

    public PokemonServiceImpl(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    @Override
    public GenericResponse<List<PokemonEntity>> pokemonList() {
        List<PokemonEntity> allPokemonList = pokemonRepo.getAllPokemonList();
        return new GenericResponse<>(Collections.unmodifiableList(allPokemonList));
    }
}
