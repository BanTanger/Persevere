package com.bantanger.dao;

import com.bantanger.entity.Person;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
public interface PersonDao {

    Person save(Person person);

    List<Person> findPersonsByFirstnameQueryDSL(String firstName);
}
