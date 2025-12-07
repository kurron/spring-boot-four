package org.kurron.spring.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
@AllArgsConstructor
class ExercisePersistence implements CommandLineRunner {

    private final AlphaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Exercise Persistence started");
        Set<Delta> deltas = IntStream.range(0, 5).mapToObj(i -> Delta.builder().name(randomName()).build()).collect(Collectors.toSet());
        Set<Charlie> charlies = IntStream.range(0, 5).mapToObj(i -> Charlie.builder().name(randomName()).build()).collect(Collectors.toSet());
        Set<Bravo> bravos = IntStream.range(0, 5).mapToObj(i -> Bravo.builder().name(randomName()).charlies(charlies).deltas(deltas).build()).collect(Collectors.toSet());
        var alpha = Alpha.builder().name(randomName()).bravos(bravos).build();
        var saved = repository.save(alpha);
        log.info("Saved entity: {}", saved);
        var loaded = repository.findByName(saved.name());
        log.info("Loaded entity: {}", loaded);
    }

    private static String randomName() {
        return Long.toHexString(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE)).toUpperCase();
    }
}
