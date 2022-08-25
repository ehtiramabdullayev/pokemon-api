package org.pokemon.example.repo;

import org.junit.jupiter.api.Test;
import org.pokemon.example.model.PokemonEntity;
import org.pokemon.example.repo.impl.PokemonRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PokemonRepoTest {
    @Test
    public void test_whenTryToSaveTokenSuccess() {
        PokemonRepo authRepository = new PokemonRepositoryImpl();
        PokemonEntity entity = new PokemonEntity("name", "type", "type2", 100, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        assertTrue(authRepository.savePokemon("1", entity));
    }

    @Test
    public void test_whenTryGetTokenListSuccess() {
        PokemonRepo authRepository = new PokemonRepositoryImpl();
        PokemonEntity entity = new PokemonEntity("name", "type", "type2", 100, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        PokemonEntity entity2 = new PokemonEntity("name2", "type2", "type3", 200, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        assertTrue(authRepository.savePokemon("1", entity));
        assertTrue(authRepository.savePokemon("2", entity2));
        assertTrue(authRepository.getAllPokemonList().isPresent());
        assertEquals(2, authRepository.getAllPokemonList().get().size());
    }
}