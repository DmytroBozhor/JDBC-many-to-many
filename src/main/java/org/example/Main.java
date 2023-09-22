package org.example;

import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        OrderService orderService = (OrderService) applicationContext.getBean("orderService");

        Order order = orderService.get(1).get();

        System.out.println(order);

    }
}