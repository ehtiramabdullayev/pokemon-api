package org.pokemon.example.parser;

import org.pokemon.example.dto.Pokemon;

import java.util.Optional;

public class PokemonParser {

    Optional<Pokemon> parsePokemonData(String line) {
        try {
            Pokemon pokemon = validatePokemon(line);
            return Optional.of(pokemon);
        } catch (Exception e) {
            System.out.println("Detected invalid line, skipping..." + line);
            System.out.println(e.getMessage());
            System.out.println();
        }
        return Optional.empty();
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
            throw new IllegalArgumentException("Illegal value passed to one or more parameters");
        }

        return new Pokemon(id, name, typeOne, typeTwo, total, hp, attack, defense, spAttack, spDefense, speed, generation, isLegendary);
    }

}
