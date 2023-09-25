package org.example;

import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().addAnnotatedClass(Passport.class).addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("boo", 25);
            Passport passport = new Passport(person, 52463);

            person.setPassport(passport);

            session.save(person); // passport will also be saved to the database thanks to @Cascade annotation

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        sessionFactory.close();
    }
}