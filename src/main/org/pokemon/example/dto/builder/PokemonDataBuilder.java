package org.pokemon.example.dto.builder;

import org.pokemon.example.dto.Pokemon;

public class PokemonDataBuilder {

    public static Pokemon returnSteelCase(Pokemon pokemon) {
        int increasedHp = pokemon.getHp() * 2;
        return new Pokemon(pokemon.getId(),
                pokemon.getName(),
                pokemon.getFirstType(),
                pokemon.getSecondType(),
                pokemon.getTotal(),
                increasedHp,
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpAttack(),
                pokemon.getSpDefense(),
                pokemon.getSpeed(),
                pokemon.getGeneration(),
                pokemon.isLegendary());
    }

    public static Pokemon returnFireCase(Pokemon pokemon) {
        double loweredAttack = pokemon.getAttack() - (pokemon.getAttack() * 0.1);
        return new Pokemon(pokemon.getId(),
                pokemon.getName(),
                pokemon.getFirstType(),
                pokemon.getSecondType(),
                pokemon.getTotal(),
                pokemon.getHp(),
                loweredAttack,
                pokemon.getDefense(),
                pokemon.getSpAttack(),
                pokemon.getSpDefense(),
                pokemon.getSpeed(),
                pokemon.getGeneration(),
                pokemon.isLegendary());
    }

    public static Pokemon returnBugAndFlyingCase(Pokemon pokemon) {
        double increasedSpeed = pokemon.getSpeed() + (pokemon.getSpeed() * 0.1);
        return new Pokemon(pokemon.getId(),
                pokemon.getName(),
                pokemon.getFirstType(),
                pokemon.getSecondType(),
                pokemon.getTotal(),
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpAttack(),
                pokemon.getSpDefense(),
                increasedSpeed,
                pokemon.getGeneration(),
                pokemon.isLegendary());
    }

    public static Pokemon returnTypeStartsWithLetterGCase(Pokemon pokemon) {
        int letterCount = pokemon.getName().length();
        double increasedDefense = pokemon.getDefense() + (5 * letterCount);
        return new Pokemon(pokemon.getId(),
                pokemon.getName(),
                pokemon.getFirstType(),
                pokemon.getSecondType(),
                pokemon.getTotal(),
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpAttack(),
                increasedDefense,
                pokemon.getSpeed(),
                pokemon.getGeneration(),
                pokemon.isLegendary());
    }
}
