package com.example.sa2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Security;

@SpringBootApplication
@RestController

public class Sa2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sa2Application.class, args);
    }

    @RequestMapping("/")
    public String sayHello(){
        return String.format( "start");
    }

}
