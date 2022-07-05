package org.pokemon.example.service.transformation.function;

import org.pokemon.example.dto.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Component
public class AttackSpeedTransformationFunction implements TransformationFunction<Pokemon> {

    @Value("#{new Double('${transformation.attack_speed.value}')}")
    private Double changeValue;

    @Value("${transformation.attack_speed.first_type}")
    private String firstType;

    @Value("${transformation.attack_speed.second_type}")
    private String secondType;

    private final UnaryOperator<Pokemon> unaryOperator = pokemon -> {
        pokemon.setSpeed(pokemon.getSpeed() * changeValue);
        return pokemon;
    };

    @Override
    public boolean couldApply(Pokemon pokemon) {
        return pokemon.isTypeOf(firstType) && pokemon.isTypeOf(secondType);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

}
