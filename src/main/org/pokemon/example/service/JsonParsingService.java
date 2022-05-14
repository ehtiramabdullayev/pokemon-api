package org.pokemon.example.service;

public interface JsonParsingService {
    <T> T fromJSonToPOJO(String jsonString, Class<T> classType);
    String toJsonPOJO(Object data);
}
