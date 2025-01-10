package com.bantanger.dao;

import com.bantanger.entity.Person;
import com.bantanger.entity.QPerson;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */

@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public List<Person> findPersonsByFirstnameQueryDSL(String firstName) {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .where(person.firstName.eq(firstName))
                .fetch();
    }
}
