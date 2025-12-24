package org.kurron.spring.resilience;

/**
 * Represents a failure that could be retried.
 */
public class SoftFailure extends RuntimeException {
    public SoftFailure(String message) {
        super(message);
    }
}
