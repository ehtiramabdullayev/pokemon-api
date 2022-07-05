package org.pokemon.example.parser;

import org.pokemon.example.dto.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Pokemon parsePokemonData(String line) {
        try {
            return validatePokemon(line);
        } catch (Exception e) {
            logger.error("parseLine - failed to parse line", e);
            return null;
        }
    }

    private Pokemon validatePokemon(String line) throws IllegalArgumentException {
        String[] pokemonRecord = line.split(",");
        if (pokemonRecord.length != 13) {
            throw new IllegalArgumentException("Argument size has not matched, only 13 can be passed");
        }
        int id = Integer.parseInt(pokemonRecord[0]);
        String name = pokemonRecord[1];
        String typeOne = pokemonRecord[2];
        String typeTwo = pokemonRecord[3];
        int total = Integer.parseInt(pokemonRecord[4]);
        int hp = Integer.parseInt(pokemonRecord[5]);
        int attack = Integer.parseInt(pokemonRecord[6]);
        int defense = Integer.parseInt(pokemonRecord[7]);
        int spAttack = Integer.parseInt(pokemonRecord[8]);
        int spDefense = Integer.parseInt(pokemonRecord[9]);
        int speed = Integer.parseInt(pokemonRecord[10]);
        int generation = Integer.parseInt(pokemonRecord[11]);
        boolean isLegendary = Boolean.parseBoolean(pokemonRecord[12]);

        if (id == 0 || name.isEmpty()) {
            logger.error("Illegal value- failed to validate value");
            throw new IllegalArgumentException("Illegal value passed to one or more parameters");
        }

        return new Pokemon(id, name, typeOne, typeTwo, total, hp, attack, defense, spAttack, spDefense, speed, generation, isLegendary);
    }

}
