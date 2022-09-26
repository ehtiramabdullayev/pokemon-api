package org.pokemon.example.service;

import org.pokemon.example.api.model.response.GenericResponse;
import org.pokemon.example.api.model.response.Response;
import org.pokemon.example.dto.Pokemon;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.PokemonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"pokemons"})
public class PokemonService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PokemonRepo pokemonRepo;

    public PokemonService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }


    @Cacheable // caches the result of findAll() method
    public GenericResponse<List<PokemonEntity>> pokemonList() {
        logger.info("Getting the pokemons list from the DB");
        Optional<List<PokemonEntity>> allPokemonList = Optional.of(pokemonRepo.findAll());
        return allPokemonList
                .map(pokemonEntities -> new GenericResponse<>(Collections.unmodifiableList(pokemonEntities)))
                .orElseGet(() -> new GenericResponse<>(Collections.emptyList()));
    }

    @Cacheable
    public GenericResponse<PokemonEntity> getPokemonByName(String name) {
        logger.info("Getting the pokemon [{}] from the DB by its name", name);
        GenericResponse<PokemonEntity> genericResponse = new GenericResponse<>(
                new Response(200, "Could not find the pokemon entity")
        );
        Optional<PokemonEntity> pokemonOptional = Optional.ofNullable(pokemonRepo.getPokemonEntityByName(name));
        if (pokemonOptional.isPresent()) {
            PokemonEntity pokemonEntity = pokemonOptional.get();
            genericResponse = new GenericResponse<>(pokemonEntity);
        } else logger.error("Pokemon [{}] doesn't exist the DB", name);
        return genericResponse;
    }

    @Cacheable
    public GenericResponse<List<PokemonEntity>> getPokemonByFilters(Specification<PokemonEntity> spec, Pageable page) {
        logger.info("Getting the pokemon [{}] from the DB by given criteria", spec);
        Page<PokemonEntity> filteredPokemons = pokemonRepo.findAll(spec, page);

        Optional<List<PokemonEntity>> filteredPokemonList = Optional.of(filteredPokemons.getContent());

        return filteredPokemonList
                .map(pokemonEntities -> new GenericResponse<>(Collections.unmodifiableList(pokemonEntities)))
                .orElseGet(() -> new GenericResponse<>(Collections.emptyList()));

    }

    public void storePokemon(Pokemon pokemon) {
        logger.info("Storing the pokemon name [{}] to our DB ", pokemon.getName());
        pokemonRepo.save(convertFromDtoToEntity(pokemon));
        logger.info("Pokemon [{}] stored to the DB ", pokemon);
    }

    private PokemonEntity convertFromDtoToEntity(Pokemon pokemon) {
        return new PokemonEntity(
                String.valueOf(pokemon.getId()),
                pokemon.getName(),
                pokemon.getFirstType(),
                pokemon.getSecondType(),
                pokemon.getTotal(),
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpAttack(),
                pokemon.getSpDefense(),
                pokemon.getSpeed(),
                pokemon.getGeneration(),
                pokemon.isLegendary());
    }
}
