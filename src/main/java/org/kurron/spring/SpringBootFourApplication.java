package org.kurron.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@SpringBootApplication
@EnableResilientMethods
public class SpringBootFourApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFourApplication.class, args);
    }

}
