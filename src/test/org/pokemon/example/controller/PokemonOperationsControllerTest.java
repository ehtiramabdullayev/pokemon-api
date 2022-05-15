package org.pokemon.example.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.pokemon.example.App;
import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.service.JsonParsingService;
import org.pokemon.example.service.impl.JsonParsingServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PokemonOperationsControllerTest {
    static JsonParsingService jsonParsingService;

    @BeforeAll
    public static void setup() {
        jsonParsingService = new JsonParsingServiceImpl();
        App.main(new String[1]);
    }

    @Test
    public void testTheListPokemonsSucess() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:7777/pokemon"))
                .header("Content-Type", "application/json").build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        GenericResponse genericResponse = jsonParsingService.fromJSonToPOJO(response.body(), GenericResponse.class);
        Assertions.assertEquals("SUCCESS", genericResponse.getResponse().getMessage());
        Assertions.assertEquals(200, genericResponse.getResponse().getStatus());
    }
}