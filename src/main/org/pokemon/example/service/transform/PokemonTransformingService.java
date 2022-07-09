package org.pokemon.example.service.transform;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.service.transform.function.TransformingFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PokemonTransformingService implements TransformationService<Pokemon> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final List<TransformingFunction<Pokemon>> transformingFunctions;

    public PokemonTransformingService(List<TransformingFunction<Pokemon>> transformingFunctions) {
        this.transformingFunctions = transformingFunctions;
    }

    @Override
    public Pokemon transform(Pokemon pokemon) {
        logger.info("Pokemon [{}] started transformation: ", pokemon);
        for (TransformingFunction<Pokemon> transformingFunction : transformingFunctions) {
            if (transformingFunction.couldBeApplied(pokemon)) {
                logger.info("Pokemon [{}] is transforming using [{}] function", pokemon, transformingFunction);

                pokemon = transformingFunction.apply(pokemon);

                logger.info("Transformation of pokemon [{}] is completed! using {}", pokemon, transformingFunction);
            }
            logger.info("Pokemon: [{}] finished transformation using {}!", pokemon, transformingFunction);
        }
        return pokemon;
    }
}

