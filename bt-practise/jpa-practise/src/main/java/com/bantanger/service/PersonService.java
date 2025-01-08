package com.bantanger.service;

import com.bantanger.entity.Person;
import com.bantanger.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public void dynamicProjectionsMethod() {
        Collection<Person> bantanger = repository
                .findBySubName("bantanger", Person.class);

        Collection<PersonRepository.NamesOnly> shark = repository
                .findBySubName("shark", PersonRepository.NamesOnly.class);
    }
}
