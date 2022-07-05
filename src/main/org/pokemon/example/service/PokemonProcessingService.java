package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.pokemon.example.service.transformation.PokemonTransformationService;
import org.springframework.stereotype.Service;

@Service
public class PokemonProcessingService {
    private final PokemonRepo pokemonRepo;
    private final PokemonTransformationService pokemonTransformationService;

    public PokemonProcessingService(PokemonRepo pokemonRepo,
                                    PokemonTransformationService pokemonTransformationService) {
        this.pokemonRepo = pokemonRepo;
        this.pokemonTransformationService = pokemonTransformationService;
    }

    public void processAndStorePokemon(Pokemon pokemon) {
        // this point we transform the pokemons based on their types
        Pokemon processedPokemon = pokemonTransformationService.transform(pokemon);
        pokemonRepo.savePokemon(String.valueOf(processedPokemon.getId()), new PokemonEntity(processedPokemon.getName(), processedPokemon.getFirstType(), pokemon.getSecondType(), processedPokemon.getTotal(), processedPokemon.getHp(), processedPokemon.getAttack(), processedPokemon.getDefense(), processedPokemon.getSpAttack(), processedPokemon.getSpDefense(), processedPokemon.getSpeed(), processedPokemon.getGeneration(), processedPokemon.isLegendary()));
    }
}
