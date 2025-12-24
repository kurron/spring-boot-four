package org.kurron.spring.resilience;

import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a service that is intentionally designed to be unreliable or inconsistent in its behavior.
 */
@Service
@Slf4j
class FlakyService {

    @Retryable(includes = SoftFailure.class, maxRetries = 5, timeout = 10_000)
    String retryDeclaratively()  {
        log.info("Invoking unreliable method.");
        if (ThreadLocalRandom.current().nextBoolean()) {
            log.warn("Simulating a soft failure. Will be retried again.");
            throw new SoftFailure("Simulated failure");
        }
        log.info("Successful execution.");
        return Long.toHexString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
    }
}
