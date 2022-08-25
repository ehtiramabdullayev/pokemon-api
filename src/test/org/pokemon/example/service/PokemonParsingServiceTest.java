package org.pokemon.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pokemon.example.dto.Pokemon;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Optional<Pokemon> pokemonOptional = pokemonParsingService.parsePokemonData("");
        assertFalse(pokemonOptional.isPresent());
    }

    @Test
    public void testWhenLineIsValidReturnsOptionalPokemon() {
        Optional<Pokemon> pokemonOptional = pokemonParsingService.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False");
        assertTrue(pokemonOptional.isPresent());
        assertEquals("Bulbasaur", pokemonOptional.get().getName());
        assertEquals("Grass", pokemonOptional.get().getFirstType());
        assertEquals("Poison", pokemonOptional.get().getSecondType());
    }

    @Test
    public void testWhenValidateLineWhenMoreThan13Arguments() {
        Optional<Pokemon> pokemonOptional = pokemonParsingService.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False,14THvalue");
        assertFalse(pokemonOptional.isPresent());
    }

    @Test
    public void testWhenValidatePokemonLineWhenIdIsZeroAndNameIsEmpty() {
        Optional<Pokemon> pokemonOptional = pokemonParsingService.parsePokemonData("0,,Grass,Poison,318,45,49,49,65,65,45,1,False");
        assertFalse(pokemonOptional.isPresent());
    }
}