package org.pokemon.example.service.transformation.function;

import org.pokemon.example.dto.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
public class HpTransformationFunction implements TransformationFunction<Pokemon> {

    @Value("${transformation.health.value}")
    private Double changeValue;

    @Value("#{'${transformation.health.types}'.split(',')}")
    private List<String> types;

    private final UnaryOperator<Pokemon> unaryOperator = pokemon -> {
        pokemon.setHp(pokemon.getHp() * changeValue);
        return pokemon;
    };

    @Override
    public boolean couldApply(Pokemon pokemon) {
        return types.stream().anyMatch(pokemon::isTypeOf);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

}
