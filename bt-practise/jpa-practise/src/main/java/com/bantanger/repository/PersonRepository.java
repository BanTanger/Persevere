package com.bantanger.repository;

import com.bantanger.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
public interface PersonRepository extends Repository<Person, Long> {

    Person save(Person person);
    Optional<Person> findById(long id);
    Collection<NamesOnly> findByFirstName(String firstName);
    <T> Collection<T> findBySubName(String subName, Class<T> type);

    record NamesOnly(String firstName, String subName) {

    }
}
