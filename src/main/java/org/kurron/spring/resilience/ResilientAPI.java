package org.kurron.spring.resilience;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resilient")
@AllArgsConstructor
public class ResilientAPI {
    private final FlakyService flakyService;

    @RequestMapping("/declarative")
    String doSomethingResilient() {
        return flakyService.retryDeclaratively();
    }
}
