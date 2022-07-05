package org.pokemon.example.service;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.parser.PokemonParser;
import org.pokemon.example.repo.PokemonRepo;
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

import static org.pokemon.example.dto.builder.PokemonDataBuilder.*;

@Service
public class BatchProcessingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonParser pokemonParser;
    private final PokemonRepo pokemonRepo;

    private BufferedReader input;

    @Value("classpath:pokemon.csv")
    public Resource resourceFile;


    public BatchProcessingService(PokemonParser pokemonParser, PokemonRepo pokemonRepo) {
        this.pokemonParser = pokemonParser;
        this.pokemonRepo = pokemonRepo;
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
                .filter( line -> !line.isBlank() )
                .map(pokemonParser::parsePokemonData )
                .filter( Objects::nonNull );
    }

//    public void startProcessingPokemon(String filePath) {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            //skipping the first line
//            String currentLine = bufferedReader.readLine();
//            while ((currentLine = bufferedReader.readLine()) != null) {
//                Optional<Pokemon> pokemonOptional = pokemonParser.parsePokemonData(currentLine);
//                if (pokemonOptional.isPresent()) {
//                    Pokemon pokemon = pokemonOptional.get();
//                    processPokemon(pokemon);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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



