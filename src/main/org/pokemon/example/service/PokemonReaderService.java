package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.parser.PokemonParser;
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
    private final PokemonParser pokemonParser;
    private BufferedReader input;

    @Value("classpath:pokemon.csv")
    public Resource resourceFile;


    public PokemonReaderService(PokemonParser pokemonParser) {
        this.pokemonParser = pokemonParser;
    }

    @PostConstruct
    public void init() throws IOException {
        FileReader fileReader = new FileReader(resourceFile.getFile(), StandardCharsets.UTF_8);
        input = new BufferedReader(fileReader);
    }


    @PreDestroy
    public void cleanUp() {
        try {
            input.close();
        } catch (IOException ex) {
            logger.error("cleanUp - Failed to clean up.", ex);
        }
    }


    public Stream<Pokemon> pokemons() {
        return input.lines()
                .filter(line -> !line.isBlank())
                .map(pokemonParser::parsePokemonData)
                .filter(Objects::nonNull);
    }
}



