package org.kurron.spring.web;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
class RestClientShowcase implements CommandLineRunner {
    private final RestClient restClient;

    public RestClientShowcase() {
        restClient = RestClient.builder()
                               .baseUrl("http://postman-echo.com")
                               .defaultHeader("X-Custom-Header", "Kurron")
                               .build();
    }

    @Builder
    record SomeData(String someString, int someInteger, float someFloat, boolean someBoolean) {}

    @Override
    public void run(String @NonNull ... args) {
        var getResponse = restClient.get().uri("/get?foo={foo}", "bar").retrieve().body(String.class);
        log.info("GET response: {}", getResponse);
        var deleteResponse = restClient.delete().uri("/delete?foo={foo}", Long.toHexString(System.currentTimeMillis())).retrieve().body(String.class);
        log.info("DELETE response: {}", deleteResponse);
        var data = SomeData.builder()
                           .someString("Hello, World!")
                           .someInteger(42)
                           .someFloat(3.14f)
                           .someBoolean(true)
                           .build();
        var postResponse = restClient.post()
                                     .uri("/post?foo={foo}", Long.toHexString(System.currentTimeMillis()))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .body(data)
                                     .retrieve()
                                     .body(String.class);
        log.info("POST response: {}", postResponse);

        var putResponse = restClient.put()
                .uri("/put?foo={foo}", Long.toHexString(System.currentTimeMillis()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(data)
                .retrieve()
                .body(String.class);
        log.info("PUT response: {}", putResponse);
    }
}
