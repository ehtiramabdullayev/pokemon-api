package org.pokemon.example.service.impl;

import com.google.gson.Gson;
import org.pokemon.example.service.JsonParsingService;

public class JsonParsingServiceImpl implements JsonParsingService {
    @Override
    public <T> T fromJSonToPOJO(String json, Class<T> classType) {
        return new Gson().fromJson(json, classType);
    }

    @Override
    public String toJsonPOJO(Object data) {
        return new Gson().toJson(data);
    }
}

