package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.parser.PokemonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BatchProcessingService {
    private final PokemonParser pokemonParser;

    public BatchProcessingService(PokemonParser pokemonParser) {
        this.pokemonParser = pokemonParser;
    }

    public List startProcessingPokemon(String filePath) {
        List<Pokemon> pokemonList = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            //skipping the first line
            String currentLine = bufferedReader.readLine();
            while ((currentLine = bufferedReader.readLine()) != null) {
                Optional<Pokemon> pokemonOptional = pokemonParser.parsePokemonData(currentLine);
                if (pokemonOptional.isPresent()) {
                    Pokemon pokemon = pokemonOptional.get();
                    pokemonList.add(pokemon);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return pokemonList;
    }
}
