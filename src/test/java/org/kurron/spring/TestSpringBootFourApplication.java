package org.kurron.spring;

import org.springframework.boot.SpringApplication;

public class TestSpringBootFourApplication {

    static void main(String[] args) {
        SpringApplication.from(SpringBootFourApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
