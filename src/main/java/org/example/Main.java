package org.example;

import org.example.dao.ActorDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        ActorDao actorDao = (ActorDao) applicationContext.getBean("actorDao");

        System.out.println(actorDao.get(1));

        System.out.println(actorDao.getALl());

    }
}