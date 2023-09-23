package org.example;

import org.example.model.Order;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Main {
    public static void main(String[] args) {

        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        SessionFactory sessionFactory1 = (SessionFactory) applicationContext.getBean("sessionFactory");

        Product product = sessionFactory1.getCurrentSession().get(Product.class, 1);

        System.out.println(product);*/

        Configuration configuration = new Configuration().addAnnotatedClass(Product.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Product product = session.get(Product.class, 1);
        System.out.println(product);

        session.save(new Product("cucamber", 100));

        session.getTransaction().commit();

        sessionFactory.close();
    }
}