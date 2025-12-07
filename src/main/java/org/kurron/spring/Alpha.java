package org.kurron.spring;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.Set;

// the use of Table and Column annotations is optional, as Spring does the correct thing by default
@Builder
record Alpha(@Id Long id, String name, Set<Bravo> bravos) {}
