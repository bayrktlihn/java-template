package io.bayrktlihn.template.config;

import io.bayrktlihn.template.entity.Person;
import io.bayrktlihn.template.enums.Gender;
import io.bayrktlihn.template.util.date.Dates;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

class JpaConfigurationTest {

    @Test
    void createEntity() {
        EntityManagerFactory entityManagerFactory = JpaConfiguration.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Person person = new Person();
        person.setFirstName("alihan");
        person.setLastName("bayraktar");
        person.setBirthDate(Dates.createStartOfDay(1995, 10, 4));
        person.setGender(Gender.MAN);

        entityManager.persist(person);

        tx.commit();

        entityManager.close();

    }

    @Test
    void createUpdate() {
        createEntity();

        EntityManagerFactory entityManagerFactory = JpaConfiguration.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        Person person = entityManager.find(Person.class, 1L);
        person.setBirthDate(Dates.create(1995, 11, 4));
        tx.begin();

        tx.commit();
    }

}
