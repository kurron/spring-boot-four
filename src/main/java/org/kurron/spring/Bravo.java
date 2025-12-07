package org.kurron.spring;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.Set;

// the use of Table and Column annotations is optional, as Spring does the correct thing by default
@Builder
record Bravo(@Id Long id, String name, Long alphaId, Set<Charlie> charlies, Set<Delta> delta) {}
