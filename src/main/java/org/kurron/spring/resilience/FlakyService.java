package org.kurron.spring.resilience;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryListener;
import org.springframework.core.retry.RetryOperations;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a service that is intentionally designed to be unreliable or inconsistent in its behavior.
 */
@Service
@Slf4j
class FlakyService {

    private final RetryOperations retryOperations;

    public FlakyService(RetryListener retryListener) {
        var policy = RetryPolicy.builder().maxRetries(5).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
        var template = new RetryTemplate(policy);
        template.setRetryListener(retryListener);
        retryOperations = template;
    }

    @Retryable(includes = SoftFailure.class, maxRetries = 5, timeout = 10_000)
    String retryDeclaratively()  {
        return beFlaky();
    }

    private static String beFlaky() {
        log.info("Invoking unreliable method.");
        if (ThreadLocalRandom.current().nextBoolean()) {
            log.warn("Simulating a soft failure. Will be retried again.");
            throw new SoftFailure("Simulated failure");
        }
        log.info("Successful execution.");
        return Long.toHexString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
    }

    String retryImperatively() throws RetryException {
        return retryOperations.execute(FlakyService::beFlaky);

    }
}
