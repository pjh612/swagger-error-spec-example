package com.example.exampleapi;

import com.example.commonswagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
public class ExampleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApiApplication.class, args);
    }

}
