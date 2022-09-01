package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PokemonReadingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonParsingService pokemonParsingService;
    private List<Pokemon> pokemonList;
    @Value("${pokemon_csv_file_path}")
    public Resource resourceFile;

    public PokemonReadingService(PokemonParsingService pokemonParsingService) {
        this.pokemonParsingService = pokemonParsingService;
    }

    @PostConstruct
    public void init() {
        try (FileReader fileReader = new FileReader(resourceFile.getFile(), StandardCharsets.UTF_8)) {
            pokemonList = pokemonParsingService.parsePokemonData(fileReader);
        } catch (IOException e) {
            logger.error("Error reading file", e);
            pokemonList = List.of();
        }
    }


    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
}



