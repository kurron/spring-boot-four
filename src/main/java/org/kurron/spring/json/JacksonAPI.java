package org.kurron.spring.json;

import com.fasterxml.jackson.annotation.JsonView;
import org.jspecify.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/jackson")
public class JacksonAPI {
    private final RestClient restClient;

    public JacksonAPI() {
        restClient = RestClient.builder().baseUrl("http://postman-echo.com").build();
    }

    @RequestMapping(path = "/full", produces = "application/json")
    SomeData fullObject() {
        return buildDataObject();
    }

    @RequestMapping(path = "/just-text", produces = "application/json")
    @JsonView(Views.JustText.class)
    SomeData justText() {
        return buildDataObject();
    }

    @RequestMapping(path = "/just-numbers", produces = "application/json")
    @JsonView(Views.JustNumbers.class)
    SomeData justNumbers() {
        return buildDataObject();
    }

    @RequestMapping(path = "/just-booleans", produces = "application/json")
    @JsonView(Views.JustBooleans.class)
    SomeData justBooleans() {
        return buildDataObject();
    }

    @RequestMapping(path = "/just-time", produces = "application/json")
    @JsonView(Views.JustTime.class)
    SomeData justTime() {
        return buildDataObject();
    }

    @RequestMapping(path = "/just-enumerations", produces = "application/json")
    @JsonView(Views.JustEnumerations.class)
    SomeData justEnumerations() {
        return buildDataObject();
    }

    @RequestMapping(path = "/send-full", produces = "application/json")
    String sendFullObject() {
        var data =  buildDataObject();
        return restClient.post()
                         .uri("/post")
                         .body(data)
                         .retrieve()
                         .body(String.class);
    }

    @RequestMapping(path = "/send-text", produces = "application/json")
    String sendJustText() {
        return sendEchoRequest(Views.JustText.class);
    }

    @RequestMapping(path = "/send-numbers", produces = "application/json")
    String sendJustNumbers() {
        return sendEchoRequest(Views.JustNumbers.class);
    }

    @RequestMapping(path = "/send-booleans", produces = "application/json")
    String sendJustBooleans() {
        return sendEchoRequest(Views.JustBooleans.class);
    }

    @RequestMapping(path = "/send-time", produces = "application/json")
    String sendJustTime() {
        return sendEchoRequest(Views.JustTime.class);
    }

    @RequestMapping(path = "/send-enumerations", produces = "application/json")
    String sendJustEnumerations() {
        return sendEchoRequest(Views.JustEnumerations.class);
    }

    private @Nullable String sendEchoRequest(Class<?> viewClass) {
        return restClient.post()
                .uri("/post")
                .hint(JsonView.class.getName(), viewClass)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildDataObject())
                .retrieve()
                .body(String.class);
    }

    private static SomeData buildDataObject() {
        var possibilities = SomeData.Colors.values();
        var selected = possibilities[ThreadLocalRandom.current().nextInt(possibilities.length)];
        return SomeData.builder()
                       .someString(Long.toHexString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)))
                       .someBoolean(ThreadLocalRandom.current().nextBoolean())
                       .someDateTime(LocalDateTime.now())
                       .someFloat(ThreadLocalRandom.current().nextFloat())
                       .someInteger(ThreadLocalRandom.current().nextInt())
                       .someColor(selected)
                       .build();
    }
}
