package org.pokemon.example.service.transforming.function;

import org.pokemon.example.dto.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.function.UnaryOperator;

@Component
public class DefenceTransformingFunction implements TransformingFunction<Pokemon> {

    @Value("${transforming.defence.value}")
    private Double letterValue;

    @Value("${transforming.defence.excluded_letter}")
    private Character excludedLetter;

    @Value("${transforming.defence.starts_with}")
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
    public boolean couldBeApplied(Pokemon pokemon) {
        return pokemon.getName() != null && pokemon.getName().startsWith(startsWith);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

    @Override
    public String toString() {
        return "DefenceTransformingFunction";
    }
}
