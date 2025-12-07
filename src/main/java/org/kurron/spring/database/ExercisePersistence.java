package org.kurron.spring.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@AllArgsConstructor
class ExercisePersistence implements CommandLineRunner {

    private final AlphaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Exercise Persistence started");
        var delta = Delta.builder().name(randomName()).build();
        var charlie = Charlie.builder().name(randomName()).build();
        var bravo = Bravo.builder().name(randomName()).charlies(Set.of(charlie)).deltas(Set.of(delta)).build();
        var alpha = Alpha.builder().name(randomName()).bravos(Set.of(bravo)).build();
        var saved = repository.save(alpha);
        log.info("Saved entity: {}", saved);
    }

    private static String randomName() {
        return Long.toHexString(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE)).toUpperCase();
    }
}
