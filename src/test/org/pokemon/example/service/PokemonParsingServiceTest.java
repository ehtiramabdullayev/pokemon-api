package org.pokemon.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pokemon.example.dto.Pokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PokemonParsingServiceTest {
    private PokemonParsingService pokemonParsingService;

    @BeforeEach
    public void beforeEach() {
        pokemonParsingService = new PokemonParsingService();
    }

    @AfterEach
    public void executedAfterEach() {
        pokemonParsingService = null;
    }

    @Test
    public void testWhenLineIsInvalidReturnsEmptyOptional() {
        Pokemon pokemon = pokemonParsingService.parsePokemonData("");
        assertNull(pokemon);
    }

    @Test
    public void testWhenLineIsValidReturnsOptionalPokemon() {
        Pokemon pokemon = pokemonParsingService.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False");
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals("Grass", pokemon.getFirstType());
        assertEquals("Poison", pokemon.getSecondType());
    }

    @Test
    public void testWhenValidateLineWhenMoreThan13Arguments() {
        Pokemon pokemon = pokemonParsingService.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False,14THvalue");
        assertNull(pokemon);
    }

    @Test
    public void testWhenValidatePokemonLineWhenIdIsZeroAndNameIsEmpty() {
        Pokemon pokemon = pokemonParsingService.parsePokemonData("0,,Grass,Poison,318,45,49,49,65,65,45,1,False");
        assertNull(pokemon);
    }
}