package org.pokemon.example.service.transforming.function;

import org.pokemon.example.dto.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.UnaryOperator;

@Component
public class AttackTransformingFunction implements TransformingFunction<Pokemon> {

    @Value("${transforming.attack.value}")
    private Double changeValue;

    @Value("#{'${transforming.attack.types}'.split(',')}")
    private List<String> types;

    private final UnaryOperator<Pokemon> unaryOperator = pokemon -> {
        pokemon.setAttack(pokemon.getAttack() * changeValue);
        return pokemon;
    };

    @Override
    public boolean couldBeApplied(Pokemon pokemon) {
        return types.stream().anyMatch(pokemon::isTypeOf);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

    @Override
    public String toString() {
        return "AttackTransformingFunction";
    }
}
