package org.pokemon.example.service.transformation;

import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.service.transformation.function.TransformationFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTransformationService implements TransformationService<Pokemon> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<TransformationFunction<Pokemon>> transformationFunctions;

    public PokemonTransformationService(List<TransformationFunction<Pokemon>> transformationFunctions) {
        this.transformationFunctions = transformationFunctions;
    }

    @Override
    public Pokemon transform(Pokemon pokemon) {
        logger.info("Pokemon [{}] started transformation: ", pokemon);
        for (TransformationFunction<Pokemon> transformationFunction : transformationFunctions) {
            if (transformationFunction.couldApply(pokemon)) {
                logger.info("Pokemon [{}] is transforming using [{}] function", pokemon, transformationFunction);
                pokemon = transformationFunction.apply(pokemon);
                logger.info("Transformation of pokemon [{}] is completed!", pokemon);
            }
            logger.info("Pokemon: [{}] finished transformation!", pokemon);
        }
        return pokemon;
    }
}

