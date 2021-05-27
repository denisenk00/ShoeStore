package ua.edu.j2ee.shoestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "ua.edu.j2ee.shoestore.dao",
        "ua.edu.j2ee.shoestore.model",
        "ua.edu.j2ee.shoestore.services",
        "ua.edu.j2ee.shoestore.config",
        "ua.edu.j2ee.shoestore.controllers"})
public class ShoestoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoestoreApplication.class, args);
    }

}
