package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.springframework.stereotype.Service;

import static org.pokemon.example.dto.builder.PokemonDataBuilder.*;
import static org.pokemon.example.dto.builder.PokemonDataBuilder.returnTypeStartsWithLetterGCase;

@Service
public class PokemonProcessingService {
    private final PokemonRepo pokemonRepo;

    public PokemonProcessingService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public void processPokemon(Pokemon pokemon) {
        Pokemon processedPokemon = pokemon;
        if (!pokemon.isLegendary()) {
            if (pokemon.getFirstType().equalsIgnoreCase("STEEL")) {
                processedPokemon = returnSteelCase(pokemon);
            } else if (pokemon.getFirstType().equalsIgnoreCase("Fire")) {
                processedPokemon = returnFireCase(pokemon);
            } else if (pokemon.getFirstType().equalsIgnoreCase("Bug") && pokemon.getSecondType().equalsIgnoreCase("Flying")) {
                processedPokemon = returnBugAndFlyingCase(pokemon);
            } else if (pokemon.getName().startsWith("G")) {
                processedPokemon = returnTypeStartsWithLetterGCase(pokemon);
            }
        }
        pokemonRepo.savePokemon(String.valueOf(processedPokemon.getId()), new PokemonEntity(processedPokemon.getName(), processedPokemon.getFirstType(), pokemon.getSecondType(), processedPokemon.getTotal(), processedPokemon.getHp(), processedPokemon.getAttack(), processedPokemon.getDefense(), processedPokemon.getSpAttack(), processedPokemon.getSpDefense(), processedPokemon.getSpeed(), processedPokemon.getGeneration(), processedPokemon.isLegendary()));
    }
}
