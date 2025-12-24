package org.kurron.spring.resilience;

/**
 * Represents a failure that should not be retried.
 */
public class HardFailure extends RuntimeException {
}
