package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.parser.PokemonParser;
import org.pokemon.example.repo.PokemonRepo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;

import static org.pokemon.example.dto.builder.PokemonDataBuilder.*;

public class BatchProcessingService {
    private final PokemonParser pokemonParser;
    private final PokemonRepo pokemonRepo;


    public BatchProcessingService(PokemonParser pokemonParser, PokemonRepo pokemonRepo) {
        this.pokemonParser = pokemonParser;
        this.pokemonRepo = pokemonRepo;
    }

    public void startProcessingPokemon(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            //skipping the first line
            String currentLine = bufferedReader.readLine();
            while ((currentLine = bufferedReader.readLine()) != null) {
                Optional<Pokemon> pokemonOptional = pokemonParser.parsePokemonData(currentLine);
                if (pokemonOptional.isPresent()) {
                    Pokemon pokemon = pokemonOptional.get();
                    processPokemon(pokemon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processPokemon(Pokemon pokemon) {
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



