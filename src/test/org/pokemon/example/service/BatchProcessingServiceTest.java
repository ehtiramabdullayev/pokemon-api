package org.pokemon.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.pokemon.example.parser.PokemonParser;
import org.pokemon.example.repo.impl.PokemonRepositoryImpl;

import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchProcessingServiceTest {
    private BatchProcessingService processingService;


    @BeforeEach
    public void beforeEach() {
        processingService = new BatchProcessingService(new PokemonParser(), new PokemonRepositoryImpl());
    }

    @AfterEach
    public void executedAfterEach() {
        processingService = null;
    }

}