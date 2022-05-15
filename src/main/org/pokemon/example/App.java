package org.pokemon.example;

import org.pokemon.example.controller.PokemonOperationsController;
import org.pokemon.example.controller.impl.PokemonOperationsControllerImpl;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.parser.PokemonParser;
import org.pokemon.example.repo.PokemonRepo;
import org.pokemon.example.repo.impl.PokemonRepositoryImpl;
import org.pokemon.example.service.BatchProcessingService;
import org.pokemon.example.service.impl.JsonParsingServiceImpl;
import org.pokemon.example.service.impl.PokemonServiceImpl;

import java.util.List;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    public static final int MAIN_PORT = 7777;


    public static void main(String[] args) {
        PokemonRepo pokemonRepo = new PokemonRepositoryImpl();

        BatchProcessingService processingService = new BatchProcessingService(new PokemonParser(), pokemonRepo);
        processingService.startProcessingPokemon("./Data/pokemon.csv");

        List<PokemonEntity> allPokemonList = pokemonRepo.getAllPokemonList();
        System.out.println(allPokemonList.size());

        port(MAIN_PORT);
        before((request, response) -> response.type("application/json"));
        initRoutes(pokemonRepo);
    }

    public static void initRoutes(PokemonRepo pokemonRepo) {
        PokemonServiceImpl pokemonService = new PokemonServiceImpl(pokemonRepo);
        PokemonOperationsController controller = new PokemonOperationsControllerImpl(pokemonService, new JsonParsingServiceImpl());
        get("/hello", (req, res) -> "Hello Axual");
        get("/pokemon", controller::getPokemonList);
    }
}
