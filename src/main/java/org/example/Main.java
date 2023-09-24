package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().addAnnotatedClass(Item.class).addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            System.out.println(person);

            List<Item> itemList = person.getItemList();

            itemList.forEach(System.out::println);


            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        sessionFactory.close();
    }
}