package com.bantanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner runner(PersonRepository repository) {
//        return args -> {
//            Person person = new Person();
//            person.setFirstName("John");
//            repository.save(person);
//            repository.findById(person.getId()).orElseThrow(NoSuchElementException::new);
//        };
//    }
}
