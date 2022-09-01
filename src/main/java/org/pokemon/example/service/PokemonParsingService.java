package org.pokemon.example.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.pokemon.example.dto.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class PokemonParsingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Pokemon> parsePokemonData(FileReader fileReader) throws IOException {
        logger.debug("Started reading pokemons from file");
        CsvToBean<Pokemon> csvToBean =
                new CsvToBeanBuilder<Pokemon>(fileReader)
                        .withType(Pokemon.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
        logger.debug("Finished reading pokemons from file");
        return csvToBean.stream().filter(exclude().negate()).collect(Collectors.toList());
    }


    private Predicate<Pokemon> exclude() {
        return pokemon -> {
            if (pokemon.getId() == 0 || pokemon.getName().isEmpty()) {
                return true;
            }
            return pokemon.isLegendary() ||
                    pokemon.getFirstType().equalsIgnoreCase("Ghost") ||
                    pokemon.getSecondType().equalsIgnoreCase("Ghost");
        };
    }

}
