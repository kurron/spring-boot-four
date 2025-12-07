package org.kurron.spring.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@AllArgsConstructor
class ExercisePersistence implements CommandLineRunner {

    private final AlphaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Exercise Persistence started");
        var delta = Delta.builder().name("Delta").build();
        var charlie = Charlie.builder().name("Charlie").build();
        var bravo = Bravo.builder().name("Bravo").charlies(Set.of(charlie)).build();
        var alpha = Alpha.builder().name("alpha").bravos(Set.of(bravo)).build();
        var saved = repository.save(alpha);
        log.info("Saved entity: {}", saved);
    }
}
