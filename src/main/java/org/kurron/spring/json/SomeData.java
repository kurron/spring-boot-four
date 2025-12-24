package org.kurron.spring.json;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
record SomeData(@JsonView(Views.JustText.class) String someString,
                @JsonView(Views.JustNumbers.class) int someInteger,
                @JsonView(Views.JustNumbers.class) float someFloat,
                @JsonView(Views.JustBooleans.class) boolean someBoolean,
                @JsonView(Views.JustTime.class) LocalDateTime someDateTime,
                @JsonView(Views.JustEnumerations.class) Colors someColor) {
    enum Colors {
        RED, GREEN, BLUE
    }
}
