package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class PokemonReaderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonParserService pokemonParserService;
    private BufferedReader input;
    @Value("${pokemon_csv_file_path}")
    public Resource resourceFile;

    public PokemonReaderService(PokemonParserService pokemonParserService) {
        this.pokemonParserService = pokemonParserService;
    }

    @PostConstruct
    public void init() throws IOException {
        FileReader fileReader = new FileReader(resourceFile.getFile(), StandardCharsets.UTF_8);
        input = new BufferedReader(fileReader);
    }

    @PreDestroy
    public void cleanUp() {
        try {
            logger.info("closing the reader...");
            input.close();
        } catch (IOException ex) {
            logger.error("cleanUp - Failed to clean up.", ex);
        }
    }

    public Stream<Pokemon> readPokemons() {
        return input.lines()
                .filter(line -> !line.isBlank())
                .map(pokemonParserService::parsePokemonData)
                .filter(Objects::nonNull);
    }
}



