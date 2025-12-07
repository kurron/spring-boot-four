package org.kurron.spring;

import lombok.Builder;
import org.springframework.data.annotation.Id;

// the use of Table and Column annotations is optional, as Spring does the correct thing by default
@Builder
record Charlie(@Id Long id, String name, Long bravoId) {}
