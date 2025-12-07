package org.kurron.spring;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

// the use of Table and Column annotations is optional, as Spring does the correct thing by default
@Builder
@With
record Bravo(@Id Long id, String name, Long alphaId, Set<Charlie> charlies, Set<Delta> deltas) {
    Bravo {
        charlies = charlies == null ? new HashSet<>(8) : charlies;
        deltas = deltas == null ? new HashSet<>(8) : deltas;
    }
}
