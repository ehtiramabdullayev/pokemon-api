//package org.pokemon.example.parser;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.pokemon.example.dto.Pokemon;
//
//import java.io.PrintStream;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PokemonParserTest {
//    private PokemonParser pokemonParser;
//    private PrintStream out;
//
//
//    @BeforeEach
//    public void beforeEach() {
//        pokemonParser = new PokemonParser();
//        out = Mockito.mock(PrintStream.class);
//        System.setOut(out);
//    }
//
//    @AfterEach
//    public void executedAfterEach() {
//        pokemonParser = null;
//    }
//
//    @Test
//    public void testWhenLineIsInvalidReturnsEmptyOptional() {
//        Optional<Pokemon> userOptional = pokemonParser.parsePokemonData("");
//        assertEquals(Optional.empty(), userOptional);
//    }
//
//    @Test
//    public void testWhenLineIsValidReturnsOptionalPokemon() {
//        Optional<Pokemon> userOptional = pokemonParser.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False");
//        assertTrue(userOptional.isPresent());
//    }
//
//    @Test
//    public void testWhenValidateLineWhenMoreThan13Arguments() {
//        pokemonParser.parsePokemonData("1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False,14THvalue");
//        Mockito.verify(out).println("Argument size has not matched, only 13 can be passed");
//    }
//
//    @Test
//    public void testWhenValidatePokemonLineWhenIdIsZeroAndNameIsEmpty() {
//        pokemonParser.parsePokemonData("0,,Grass,Poison,318,45,49,49,65,65,45,1,False");
//        Mockito.verify(out).println("Illegal value passed to one or more parameters");
//
//    }
//}