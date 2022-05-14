package org.pokemon.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pokemon.example.parser.PokemonParser;

import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchProcessingServiceTest {
    private BatchProcessingService processingService;


    @BeforeEach
    public void beforeEach() {
        processingService = new BatchProcessingService(new PokemonParser());
    }

    @AfterEach
    public void executedAfterEach() {
        processingService = null;
    }


    @Test
    public void testNumberOfLinesFromDataSource() {
        BatchProcessingService processingService = new BatchProcessingService(new PokemonParser());
        List list = processingService.startProcessingPokemon("./Data/pokemon.csv");
        assertEquals(800, list.size());
    }
}