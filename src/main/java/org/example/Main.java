package org.example;

import org.example.service.ActorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        ActorService actorService = (ActorService) applicationContext.getBean("actorService");

        System.out.println(actorService.get(1));

        System.out.println(actorService.findAll());

        actorService.findActorsByNameStartingWith("B").forEach(System.out::println);

        actorService.findActorsByAgeBetween(40, 50).forEach(System.out::println);

        actorService.customQuery().forEach(System.out::println);

    }
}