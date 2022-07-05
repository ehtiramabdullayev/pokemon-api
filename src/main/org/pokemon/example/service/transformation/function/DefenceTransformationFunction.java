package org.pokemon.example.service.transformation.function;

import org.pokemon.example.dto.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Component
public class DefenceTransformationFunction implements TransformationFunction<Pokemon> {

    @Value("${transformation.defence.value}")
    private Double letterValue;

    @Value("${transformation.defence.excluded_letter}")
    private Character excludedLetter;

    @Value("${transformation.defence.starts_with}")
    private String startsWith;

    private final UnaryOperator<Pokemon> unaryOperator = pokemon -> {
        int lettersCount = pokemon.getName()
                .replace(excludedLetter + "", "")
                .replace(" ", "")
                .length();
        pokemon.setDefense(pokemon.getDefense() + lettersCount * letterValue);
        return pokemon;
    };

    @Override
    public boolean couldApply(Pokemon pokemon) {
        return pokemon.getName() != null && pokemon.getName().startsWith(startsWith);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

}
