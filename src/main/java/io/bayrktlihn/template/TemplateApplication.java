package io.bayrktlihn.template;

import io.bayrktlihn.template.config.JpaConfiguration;
import io.bayrktlihn.template.entity.Person;
import io.bayrktlihn.template.entity.Person_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TemplateApplication {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = JpaConfiguration.getEntityManagerFactory();


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        query.select(root).where(cb.equal(root.get(Person_.FIRST_NAME), "alihan"));


        TypedQuery<Person> theQuery = entityManager.createQuery(query);
        Person singleResult = theQuery.getSingleResult();


        System.out.println(singleResult);

    }
}
