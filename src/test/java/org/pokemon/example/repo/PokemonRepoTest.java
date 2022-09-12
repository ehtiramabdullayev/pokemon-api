package org.pokemon.example.repo;

import org.junit.jupiter.api.Test;
import org.pokemon.example.model.PokemonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class PokemonRepoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PokemonRepo authRepository;

    @Test
    public void test_whenTryToSaveTokenSuccess() {
        PokemonEntity entity = new PokemonEntity("1", "name", "type", "type2", 100, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        PokemonEntity savedEntity = authRepository.save(entity);
        assertNotNull(savedEntity);
        assertEquals("name", savedEntity.getName());
    }

    @Test
    public void test_whenTryGetTokenListSuccess() {
        PokemonEntity entity = new PokemonEntity("1", "name", "type", "type2", 100, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        PokemonEntity entity2 = new PokemonEntity("2", "name2", "type2", "type3", 200, 50, 55.5, 60.5, 58, 62, 70, 90, false);
        PokemonEntity firstSavedEntity = authRepository.save(entity);
        PokemonEntity secondSavedEntity = authRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);
        assertFalse(authRepository.findAll().isEmpty());
        assertEquals(2, authRepository.findAll().size());
    }
}