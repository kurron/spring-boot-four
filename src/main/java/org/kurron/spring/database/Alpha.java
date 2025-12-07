package org.kurron.spring.database;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

// the use of Table and Column annotations is optional, as Spring does the correct thing by default
@Builder
@With
record Alpha(@Id Long id, String name, @MappedCollection(idColumn = "alpha_id") Set<Bravo> bravos) {
    Alpha {
        bravos = bravos == null ? new HashSet<>(8) : bravos;
    }
}
